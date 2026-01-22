package com.amadeus.service.impl;

import com.amadeus.mapper.NOSMapper;
import com.amadeus.mapper.PageMapper;
import com.amadeus.pojo.PageResult;
import com.amadeus.pojo.SysChicken;
import com.amadeus.pojo.SysUser;
import com.amadeus.service.NOSService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class NOSServiceImpl implements NOSService {

    @Autowired
    private NOSMapper nosMapper;
    @Autowired
    private PageMapper pageMapper;

    /**
     * 判断用户是否存在
     */
    private SysUser ifUser(Integer user_account) {
        SysUser user = nosMapper.getUserById(user_account);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    /**
     * 判断鸡是否存在
     */
    private SysChicken ifCk(Integer user_account) {
        //先判断用户是否存在
        ifUser(user_account);
        SysChicken ck = nosMapper.getCkById(user_account);
        if (ck == null) {
            throw new RuntimeException("用户不是鸡");
        } else {
            return ck;
        }
    }

    /**
     * 判断用户是否重复
     */
    private void ifUserExist(String nick) {
        if (nosMapper.countByNick(nick) > 0) {
            throw new RuntimeException("该昵称已存在");
        }
    }

    /**
     * 判断鸡昵称是否重复
     */
    private void ifCkNickExist(String nick) {
        if (nosMapper.countCkByNick(nick) > 0) {
            throw new RuntimeException("该鸡昵称已存在");
        }
    }

    @Override
    public List<SysUser> getUser() {
        return nosMapper.getUser();
    }

    @Override
    public SysUser getUserById(Integer user_account) {
        return ifUser(user_account);
    }

    @Override
    public List<SysChicken> getCk() {
        return nosMapper.getCk();
    }

    @Override
    public SysChicken getCkById(Integer user_account) {
        return ifCk(user_account);
    }

    @Override
    public void addUser(SysUser user) {
        ifUserExist(user.getUser_nick());
        nosMapper.addUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCk(Integer user_account, SysChicken ck) {
        SysUser user = ifUser(user_account);

        if (user.getUser_fk() != null && user.getUser_fk() != 0) {
            throw new RuntimeException("该用户已是鸡");
        }
        ifCkNickExist(ck.getCk_nick());
        nosMapper.addCk(ck);
        nosMapper.LinkUserFk(user_account, ck.getCk_id());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer user_account) {
        SysUser user = ifUser(user_account);
        Integer ck_id = user.getUser_fk();
        nosMapper.deleteUser(user_account);
        if (ck_id != null && ck_id != 0) {
            nosMapper.deleteChicken(ck_id);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCk(Integer user_account) {
        SysChicken ck = ifCk(user_account);
        nosMapper.LinkUserFk(user_account, null);
        nosMapper.deleteChicken(ck.getCk_id());
    }

    @Override
    public void updateUser(Integer user_account, SysUser user) {
        SysUser changeUser = ifUser(user_account);

        if (user.getUser_nick() != null && !user.getUser_nick().equals(changeUser.getUser_nick())) {
            ifUserExist(user.getUser_nick());
            changeUser.setUser_nick(user.getUser_nick());
        }
        if (user.getUser_pwd() != null) {
            changeUser.setUser_pwd(user.getUser_pwd());
        }
        if (user.getUser_sex() != null) {
            changeUser.setUser_sex(user.getUser_sex());
        }
        if (user.getUser_age() != null) {
            changeUser.setUser_age(user.getUser_age());
        }
        if (user.getUser_imp() != null) {
            changeUser.setUser_imp(user.getUser_imp());
        }
        if (user.getUser_hobby() != null) {
            changeUser.setUser_hobby(user.getUser_hobby());
        }
        nosMapper.updateUser(changeUser);
    }

    @Override
    public void updateCk(Integer user_account,SysChicken ck) {
        SysChicken changeCk = ifCk(user_account);

        if (ck.getCk_nick() != null && !ck.getCk_nick().equals(changeCk.getCk_nick())) {
            ifCkNickExist(ck.getCk_nick());
            changeCk.setCk_nick(ck.getCk_nick());
        }
        if (ck.getCk_per() != null) {
            changeCk.setCk_per(ck.getCk_per());
        }
        if (ck.getCk_property() != null) {
            changeCk.setCk_property(ck.getCk_property());
        }
        if (ck.getCk_data_height() != null) {
            changeCk.setCk_data_height(ck.getCk_data_height());
        }
        if (ck.getCk_data_weight() != null) {
            changeCk.setCk_data_weight(ck.getCk_data_weight());
        }
        if (ck.getCk_data_breast() != null) {
            changeCk.setCk_data_breast(ck.getCk_data_breast());
        }
        if (ck.getCk_data_length() != null) {
            changeCk.setCk_data_length(ck.getCk_data_length());
        }
        nosMapper.updateCk(changeCk);
    }

    @Override
    public PageResult<SysUser> page(Integer page, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(page, pageSize);
        //执行查询
        List<SysUser> users = pageMapper.list();
        //解析查询结果并封装数据
        Page<SysUser> p = (Page<SysUser>) users;
        return new PageResult<SysUser>(p.getTotal(), p.getResult());
    }


}
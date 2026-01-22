package com.amadeus.service;

import com.amadeus.pojo.PageResult;
import com.amadeus.pojo.SysChicken;
import com.amadeus.pojo.SysUser;

import java.util.List;

public interface NOSService {

    List<SysUser> getUser();

    SysUser getUserById(Integer user_account);

    List<SysChicken> getCk();

    SysChicken getCkById(Integer user_account);

    void addUser(SysUser user);

    void addCk(Integer user_account,SysChicken ck);

    void deleteUser(Integer user_account);

    void deleteCk(Integer user_account);

    void updateUser(Integer user_account,SysUser user);

    void updateCk(Integer user_account,SysChicken ck);

    PageResult<SysUser> page(Integer page, Integer pageSize);

}

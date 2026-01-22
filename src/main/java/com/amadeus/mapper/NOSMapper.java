package com.amadeus.mapper;

import com.amadeus.pojo.SysChicken;
import com.amadeus.pojo.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NOSMapper {

    @Select("select user_account, user_pwd, user_nick, user_img, user_sex, user_age, user_imp, user_hobby,user_fk from sys_user order by user_account")
    List<SysUser> getUser();

    @Select("select user_account, user_pwd, user_nick, user_img, user_sex, user_age, user_imp, user_hobby,user_fk from sys_user where user_account = #{user_account}")
    SysUser getUserById(Integer user_account);

    @Select("select count(*) from sys_user where user_nick = #{user_nick}")
    Integer countByNick(String user_nick);

    @Select("select count(*) from sys_chicken where ck_nick = #{nick}")
    Integer countCkByNick(String nick);

    @Select("select ck_id, ck_nick, ck_times, ck_rating, ck_per, ck_property, ck_data_height, ck_data_weight, ck_data_breast, ck_data_length from sys_chicken")
    List<SysChicken> getCk();

    @Select("SELECT c.ck_id, c.ck_nick, c.ck_times, c.ck_rating, c.ck_per, c.ck_property, " +
            "c.ck_data_height, c.ck_data_weight, c.ck_data_breast, c.ck_data_length " +
            "FROM sys_user u, sys_chicken c " +
            "WHERE u.user_account = #{user_account} AND u.user_fk = c.ck_id")
    SysChicken getCkById(Integer user_account);

    @Insert("insert into sys_user(user_pwd, user_nick, user_sex, user_age, user_imp, user_hobby) " + "values (#{user_pwd}, #{user_nick}, #{user_sex}, #{user_age}, #{user_imp}, #{user_hobby})")
    void addUser(SysUser user);

    @Insert("insert into sys_chicken(ck_nick, ck_per, ck_property, ck_data_height, ck_data_weight, ck_data_breast, ck_data_length) " + "values (#{ck_nick}, #{ck_per}, #{ck_property}, #{ck_data_height}, #{ck_data_weight}, #{ck_data_breast}, #{ck_data_length})")
    @Options(useGeneratedKeys = true, keyProperty = "ck_id")
    void addCk(SysChicken ck);

    @Update("update sys_user set user_fk = #{ck_id} where user_account = #{user_account}")
    void LinkUserFk(Integer user_account, Integer ck_id);

    @Delete("delete from sys_user where user_account = #{user_account}")
    void deleteUser(Integer user_account);

    @Delete("delete from sys_chicken where ck_id = #{ck_id}")
    void deleteChicken(Integer ck_id);

    @Update("update sys_user set user_nick = #{user_nick}, user_pwd = #{user_pwd}, user_sex = #{user_sex}, user_age = #{user_age}, user_imp = #{user_imp}, user_hobby = #{user_hobby} where user_account = #{user_account}")
    void updateUser(SysUser user);

    @Update("update sys_chicken set ck_nick = #{ck_nick}, ck_per = #{ck_per}, ck_property = #{ck_property}, ck_data_height = #{ck_data_height}, ck_data_weight = #{ck_data_weight}, ck_data_breast = #{ck_data_breast}, ck_data_length = #{ck_data_length} where ck_id = #{ck_id}")
    void updateCk(SysChicken changeCk);
}

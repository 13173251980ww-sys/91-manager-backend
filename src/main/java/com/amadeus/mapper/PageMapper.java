package com.amadeus.mapper;

import com.amadeus.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PageMapper {

    //分页查询
    @Select("SELECT * FROM sys_user")
    public List<SysUser> list();
}

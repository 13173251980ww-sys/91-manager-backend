package com.amadeus.pojo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({
        "user_account",
        "user_pwd",
        "user_nick",
        "user_img",
        "user_sex",
        "user_age",
        "user_imp",
        "user_hobby"
})

@Data
public class SysUser {
    private Integer user_account;
    private String user_pwd;
    private String user_nick;
    private String user_img;
    private String user_sex;
    private Integer user_age;
    private String user_imp;
    private String user_hobby;
    private Integer user_fk;
}
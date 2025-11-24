package com.zhiyong.dormitory.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //用户名
    private String username;

    //密码
    private String password;

    //身份
    private String identity;

    //头像
    private String avatar;
}

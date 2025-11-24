package com.zhiyong.dormitory.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//系统管理员
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "admin")
public class Admin {

    //用户名
    @TableId(value = "username")
    private String username;

    //密码
    @TableField(value = "password")
    private String password;

    //昵称
    @TableField(value = "name")
    private String name;

    //性别
    @TableField(value = "gender")
    private String gender;

    //年龄
    @TableField(value = "age")
    private int age;

    //手机号
    @TableField(value = "phone_num")
    private String phoneNum;

    //邮箱
    @TableField(value = "email")
    private String email;

    //头像
    @TableField("avatar")
    private String avatar;
}

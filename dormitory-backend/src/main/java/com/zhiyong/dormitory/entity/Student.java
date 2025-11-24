package com.zhiyong.dormitory.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//学生
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "student")
public class Student {

    //用户名
    @TableId(value = "username")
    private String username;

    //密码
    @TableField("password")
    private String password;

    //姓名
    @TableField("name")
    private String name;

    //龄
    @TableField("age")
    private int age;

    //性别
    @TableField("gender")
    private String gender;

    //手机号
    @TableField("phone_num")
    private String phoneNum;

    //邮箱
    @TableField("email")
    private String email;

    //头像
    @TableField("avatar")
    private String avatar;
}

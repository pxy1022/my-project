package com.zhiyong.dormitory.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//宿舍管理员
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dorm_manager")
public class DormManager {

    //用户名
    @TableId("username")
    private String username;

    //密码
    @TableField("password")
    private String password;

    //所管理的宿舍楼栋号
    @TableField("dormbuild_id")
    private int dormBuildId;

    //名字
    @TableField("name")
    private String name;

    //性别
    @TableField("gender")
    private String gender;

    //年龄
    @TableField("age")
    private int age;

    //主键
    @TableField("phone_num")
    private String phoneNum;

    //邮箱
    @TableField("email")
    private String email;

    //头像
    @TableField("avatar")
    private String avatar;
}

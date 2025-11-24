package com.zhiyong.dormitory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//访客
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "visitor")
public class Visitor {

    //主键
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //访客名称
    @TableField("name")
    private String visitorName;

    //性别
    @TableField("gender")
    private String gender;

    //手机号
    @TableField("phone_num")
    private String phoneNum;

    //城市
    @TableField("origin_city")
    private String originCity;

    //访问时间
    @TableField("visit_time")
    private String visitTime;

    //内容
    @TableField("content")
    private String content;

}

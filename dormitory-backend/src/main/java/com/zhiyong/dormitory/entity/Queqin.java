package com.zhiyong.dormitory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//公告
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "queqin")
public class Queqin {

    //主键
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @TableField("username")
    private String username;

    @TableField("name")
    private String name;


    @TableField("content")
    private String content;
    @TableField("sname")
    private String sname;


    @TableField("fangjian_id")
    private Integer  fangjianId;

    @TableField("sid")
    private String  sid;



    @TableField("loucheng_id")
    private Integer  louchengId;
    @TableField("lou_id")
    private Integer  louId;



    @TableField("chuangwei")
    private String chuangwei;

    @TableField("release_time")
    private String releaseTime;
}

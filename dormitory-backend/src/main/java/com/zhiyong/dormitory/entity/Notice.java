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
@TableName(value = "notice")
public class Notice {

    //主键
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //主题
    @TableField("title")
    private String title;

    //内容
    @TableField("content")
    private String content;

    //作者
    @TableField("author")
    private String author;

    //发布时间
    @TableField("release_time")
    private String releaseTime;
}

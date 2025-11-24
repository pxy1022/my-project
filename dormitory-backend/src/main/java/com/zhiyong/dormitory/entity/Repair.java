package com.zhiyong.dormitory.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//报修单
@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName(value = "repair")
public class Repair {

    //主键
    @TableId(value = "id")
    private Integer id;

    //报修人
    @TableField("repairer")
    private String repairer;

    //报修宿舍楼
    @TableField("dormbuild_id")
    private int dormBuildId;

    //报修宿舍房间号
    @TableField("dormroom_id")
    private int dormRoomId;

    //表单标题
    @TableField("title")
    private String title;

    //表单标题
    @TableField("content")
    private String content;

    //订单状态（是否维修完成）
    @TableField("state")
    private String state;

    //单创建时间
    @TableField("order_buildtime")
    private String orderBuildTime;

    //订单完成时间
    @TableField("order_finishtime")
    private String orderFinishTime;
}

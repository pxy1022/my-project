package com.zhiyong.dormitory.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//房间调整
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdjustRoom {

    //主键id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //账号
    @TableField(value = "username")
    private String username;

    //姓名
    @TableField(value = "name")
    private String name;

    //当前房间
    @TableField(value = "currentroom_id")
    private int currentRoomId;

    //当前床位号
    @TableField(value = "currentbed_id")
    private int currentBedId;

    //目标房间
    @TableField(value = "towardsroom_id")
    private int towardsRoomId;

    //目标床位号
    @TableField(value = "towardsbed_id")
    private int towardsBedId;

    //申请状态
    @TableField("state")
    private String state;

    //申请时间
    @TableField("apply_time")
    private String applyTime;

    //处理时间
    @TableField("finish_time")
    private String finishTime;

}

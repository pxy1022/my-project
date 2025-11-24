package com.zhiyong.dormitory.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//宿舍房间
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dorm_room")
public class DormRoom {

    //宿舍房间号
    @TableId(value = "dormroom_id")
    private Integer dormRoomId;

    //宿舍楼号
    @TableField("dormbuild_id")
    private int dormBuildId;

    //楼层
    @TableField("floor_num")
    private int floorNum;

    //房间最大入住人数
    @TableField("max_capacity")
    private int maxCapacity;

    //当前房间入住人数
    @TableField("current_capacity")
    private int currentCapacity;

    //一号床位
    @TableField("first_bed")
    private String firstBed;

    //二号床位
    @TableField("second_bed")
    private String secondBed;

    //三号床位
    @TableField("third_bed")
    private String thirdBed;

    //四号床位
    @TableField("fourth_bed")
    private String fourthBed;

}

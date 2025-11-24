package com.zhiyong.dormitory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//费用管理
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "fee")
public class Fee {

    //主键
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //费用类型
    @TableField("fee_type")
    private String feeType;

    //楼宇号
    @TableField("dormbuild_id")
    private String dormBuildId;

    //房间号
    @TableField("dormroom_id")
    private String dormRoomId;

    //费用金额
    @TableField("amount")
    private Double amount;

    //缴费状态
    @TableField("payment_status")
    private String paymentStatus;

    //缴费时间
    @TableField("payment_time")
    private String paymentTime;

    //备注
    @TableField("remark")
    private String remark;

}


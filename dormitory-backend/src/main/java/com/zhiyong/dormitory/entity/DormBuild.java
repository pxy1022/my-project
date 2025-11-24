package com.zhiyong.dormitory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//宿舍楼
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dorm_build")
public class DormBuild {

    //主键
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //宿舍楼id
    @TableField("dormbuild_id")
    private int dormBuildId;

    //宿舍楼名称
    @TableField("dormbuild_name")
    private String dormBuildName;

    //宿舍楼详情
    @TableField("dormbuild_detail")
    private String dormBuildDetail;
}

package com.zhiyong.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyong.dormitory.entity.DormBuild;

import java.util.List;

//管理员用户 业务服务层
public interface DormBuildService extends IService<DormBuild> {

    //新增楼宇
    int addNewBuilding(DormBuild dormBuild);

    //查询楼宇
    Page find(Integer pageNum, Integer pageSize, String search);

    //更新楼宇信息
    int updateNewBuilding(DormBuild dormBuild);

    //删除楼宇信息
    int deleteBuilding(Integer id);

    List<DormBuild> getBuildingId();
}

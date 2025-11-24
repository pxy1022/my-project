package com.zhiyong.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyong.dormitory.entity.Fee;

//费用管理 业务服务层
public interface FeeService extends IService<Fee> {

    //新增费用
    int addNewFee(Fee fee);

    //查询
    Page find(Integer pageNum, Integer pageSize, String search);

    //更新费用信息
    int updateNewFee(Fee fee);

    //删除费用
    int deleteFee(Integer id);
}


package com.zhiyong.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyong.dormitory.entity.Visitor;

//访客 业务服务层
public interface VisitorService extends IService<Visitor> {

    //新增订单
    int addNewVisitor(Visitor visitor);

    //查询
    Page find(Integer pageNum, Integer pageSize, String search);

    //更新订单信息
    int updateNewVisitor(Visitor visitor);

    //删除订单
    int deleteVisitor(Integer id);
}

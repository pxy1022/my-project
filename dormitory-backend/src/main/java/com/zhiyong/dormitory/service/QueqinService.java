package com.zhiyong.dormitory.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyong.dormitory.entity.Notice;
import com.zhiyong.dormitory.entity.Queqin;

import java.util.List;

//公告 业务服务层
public interface QueqinService extends IService<Queqin> {


    //新增通知
    int addNewNotice(Queqin notice);

    //查询
    Page find(Integer pageNum, Integer pageSize, String search);

    //更新通知信息
    int updateNewNotice(Queqin notice);

    //删除通知
    int deleteNotice(Integer id);

    // 首页通知展示
    List<?> homePageNotice();

    Page finds(Integer pageNum, Integer pageSize, String username);
}

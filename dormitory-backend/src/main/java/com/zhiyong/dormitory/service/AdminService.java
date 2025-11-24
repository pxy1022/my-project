package com.zhiyong.dormitory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyong.dormitory.entity.Admin;

//管理员用户 业务服务层
public interface AdminService extends IService<Admin> {


    //管理员登陆
    Admin adminLogin(String username, String password);

    //更新管理员信息
    int updateAdmin(Admin admin);

}

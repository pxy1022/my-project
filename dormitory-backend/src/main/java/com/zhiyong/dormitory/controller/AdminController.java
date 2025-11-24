package com.zhiyong.dormitory.controller;

import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.Admin;
import com.zhiyong.dormitory.entity.User;
import com.zhiyong.dormitory.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

//管理员用户 控制层

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    //管理员登录
    @PostMapping("/login")
    public R<?> login(@RequestBody User user, HttpSession session) {

        Object o = adminService.adminLogin(user.getUsername(), user.getPassword());
        if (o != null) {
            //存入session
            session.setAttribute("Identity", "admin");
            session.setAttribute("User", o);
            return R.success(o);
        } else {
            return R.error("-1", "用户名或密码错误");
        }
    }
    // 管理员信息更新
    @PutMapping("/update")
    public R<?> update(@RequestBody Admin admin) {
        int i = adminService.updateAdmin(admin);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }
}

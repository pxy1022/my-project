package com.zhiyong.dormitory.controller;

import com.zhiyong.dormitory.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

//主页接口 控制层
@RestController
@RequestMapping("/main")
public class MainController {

    //获取身份信息
    @GetMapping("/loadIdentity")
    public R<?> loadIdentity(HttpSession session) {
        Object identity = session.getAttribute("Identity");

        if (identity != null) {
            return R.success(identity);
        } else {
            return R.error("-1", "加载失败");
        }
    }

    //获取个人信息
    @GetMapping("/loadUserInfo")
    public R<?> loadUserInfo(HttpSession session) {
        Object User = session.getAttribute("User");

        if (User != null) {
            return R.success(User);
        } else {
            return R.error("-1", "加载失败");
        }
    }

    //退出登录
    @GetMapping("/signOut")
    public R<?> signOut(HttpSession session) {
        session.removeAttribute("User");
        session.removeAttribute("Identity");
        return R.success();
    }

}

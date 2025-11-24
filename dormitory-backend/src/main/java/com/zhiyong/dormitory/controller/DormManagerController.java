package com.zhiyong.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.DormManager;
import com.zhiyong.dormitory.entity.User;
import com.zhiyong.dormitory.service.DormManagerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 宿舍管理员 控制层
 *
 * @author: ShanZhu
 * @date: 2024-01-07
 */
@RestController
@RequestMapping("/dormManager")
public class DormManagerController {

    @Resource
    private DormManagerService dormManagerService;

    /**
     * 宿管添加
     */
    @PostMapping("/add")
    public R<?> add(@RequestBody DormManager dormManager) {
        int i = dormManagerService.addNewDormManager(dormManager);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "添加失败");
        }
    }

    /**
     * 宿管信息更新
     */
    @PutMapping("/update")
    public R<?> update(@RequestBody DormManager dormManager) {
        int i = dormManagerService.updateNewDormManager(dormManager);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    /**
     * 宿管删除
     */
    @DeleteMapping("/delete/{username}")
    public R<?> delete(@PathVariable String username) {
        int i = dormManagerService.deleteDormManager(username);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }

    /**
     * 宿管查找
     */
    @GetMapping("/find")
    public R<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search) {
        Page page = dormManagerService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }

    /**
     * 宿管登录
     */
    @PostMapping("/login")
    public R<?> login(@RequestBody User user, HttpSession session) {

        Object o = dormManagerService.dormManagerLogin(user.getUsername(), user.getPassword());
        if (o != null) {
            System.out.println(o);
            //存入session
            session.setAttribute("Identity", "dormManager");
            session.setAttribute("User", o);
            return R.success(o);
        } else {
            return R.error("-1", "用户名或密码错误");
        }
    }
}

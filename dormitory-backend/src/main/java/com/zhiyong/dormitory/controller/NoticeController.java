package com.zhiyong.dormitory.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.Notice;
import com.zhiyong.dormitory.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//公告 控制层
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    //公告添加
    @PostMapping("/add")
    public R<?> add(@RequestBody Notice notice) {
        int i = noticeService.addNewNotice(notice);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "添加失败");
        }
    }

    //公告更新
    @PutMapping("/update")
    public R<?> update(@RequestBody Notice notice) {
        int i = noticeService.updateNewNotice(notice);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    //公告删除
    @DeleteMapping("/delete/{id}")
    public R<?> delete(@PathVariable Integer id) {
        int i = noticeService.deleteNotice(id);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }

    //公告查找
    @GetMapping("/find")
    public R<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search) {
        Page page = noticeService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }

    //首页公告展示
    @GetMapping("/homePageNotice")
    public R<?> homePageNotice() {
        List<?> list = noticeService.homePageNotice();
        if (list != null) {
            return R.success(list);
        } else {
            return R.error("-1", "首页公告查询失败");
        }
    }

}

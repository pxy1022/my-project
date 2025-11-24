package com.zhiyong.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.Visitor;
import com.zhiyong.dormitory.service.VisitorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//访客 控制层
@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Resource
    private VisitorService visitorService;

    //访客添加
    @PostMapping("/add")
    public R<?> add(@RequestBody Visitor visitor) {
        int i = visitorService.addNewVisitor(visitor);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "添加失败");
        }
    }

    //访客信息更新
    @PutMapping("/update")
    public R<?> update(@RequestBody Visitor visitor) {
        int i = visitorService.updateNewVisitor(visitor);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    //访客删除
    @DeleteMapping("/delete/{id}")
    public R<?> delete(@PathVariable Integer id) {
        int i = visitorService.deleteVisitor(id);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }

    //访客查询
    @GetMapping("/find")
    public R<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search) {
        Page page = visitorService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }
}

package com.zhiyong.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.Repair;
import com.zhiyong.dormitory.service.RepairService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//维修 控制层
@RestController
@RequestMapping("/repair")
public class RepairController {

    @Resource
    private RepairService repairService;

    //添加订单
    @PostMapping("/add")
    public R<?> add(@RequestBody Repair repair) {
        int i = repairService.addNewOrder(repair);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "添加失败");
        }
    }

    //更新订单
    @PutMapping("/update")
    public R<?> update(@RequestBody Repair repair) {
        int i = repairService.updateNewOrder(repair);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    //删除订单
    @DeleteMapping("/delete/{id}")
    public R<?> delete(@PathVariable Integer id) {
        int i = repairService.deleteOrder(id);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }

    //查找订单
    @GetMapping("/find")
    public R<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search) {
        Page page = repairService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }

    //个人申报报修 分页查询
    @GetMapping("/find/{name}")
    public R<?> individualFind(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search,
                               @PathVariable String name) {
        System.out.println(name);
        Page page = repairService.individualFind(pageNum, pageSize, search, name);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }

    //首页顶部：报修统计
    @GetMapping("/orderNum")
    public R<?> orderNum() {
        int num = repairService.showOrderNum();
        if (num >= 0) {
            return R.success(num);
        } else {
            return R.error("-1", "报修统计查询失败");
        }
    }

}

package com.zhiyong.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.Fee;
import com.zhiyong.dormitory.service.FeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//费用管理 控制层
@RestController
@RequestMapping("/fee")
public class FeeController {

    @Resource
    private FeeService feeService;

    //费用添加
    @PostMapping("/add")
    public R<?> add(@RequestBody Fee fee) {
        int i = feeService.addNewFee(fee);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "添加失败");
        }
    }

    //费用信息更新
    @PutMapping("/update")
    public R<?> update(@RequestBody Fee fee) {
        int i = feeService.updateNewFee(fee);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    //费用删除
    @DeleteMapping("/delete/{id}")
    public R<?> delete(@PathVariable Integer id) {
        int i = feeService.deleteFee(id);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }

    //费用查询
    @GetMapping("/find")
    public R<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search) {
        Page page = feeService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }
}


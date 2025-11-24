package com.zhiyong.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.AdjustRoom;
import com.zhiyong.dormitory.service.AdjustRoomService;
import com.zhiyong.dormitory.service.DormRoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//房间调整 控制层

@RestController
@RequestMapping("/adjustRoom")
public class AdjustRoomController {

    @Resource
    private AdjustRoomService adjustRoomService;

    @Resource
    private DormRoomService dormRoomService;



//添加订单
    @PostMapping("/add")
    public R<?> add(@RequestBody AdjustRoom adjustRoom) {

        int result = adjustRoomService.addApply(adjustRoom);
        if (result == 1) {
            return R.success();
        } else {
            return R.error("-1", "查询失败");
        }
    }


    //更新订单
    @PutMapping("/update/{state}")
    public R<?> update(@RequestBody AdjustRoom adjustRoom, @PathVariable Boolean state) {

        if (state) {
            // 更新房间表信息
            int i = dormRoomService.adjustRoomUpdate(adjustRoom);
            if (i == -2) {
                return R.error("-1", "重复操作");
            }
        }
        //更新调宿表信息
        int i = adjustRoomService.updateApply(adjustRoom);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    //删除订单
    @DeleteMapping("/delete/{id}")
    public R<?> delete(@PathVariable Integer id) {
        int i = adjustRoomService.deleteAdjustment(id);
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
        Page page = adjustRoomService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }
}

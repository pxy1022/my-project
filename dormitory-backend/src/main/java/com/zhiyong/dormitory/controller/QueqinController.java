package com.zhiyong.dormitory.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.*;
import com.zhiyong.dormitory.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//公告 控制层
@RestController
@RequestMapping("/queqin")
public class QueqinController {

    @Resource
    QueqinService queqinService;

    @Resource
    StudentService studentService;

    @Resource
    DormRoomService dormRoomService;

    @Resource
    DormBuildService dormBuildService;


    @PostMapping("/add")
    public R<?> add(@RequestBody Queqin queqin) {
        int i = queqinService.addNewNotice(queqin);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "添加失败");
        }
    }

    @GetMapping("/liststudent")
    public R<?> liststudent() {

        List<Student> list = studentService.list();

        return  R.success(list);
    }
    @GetMapping("/username")
    public R<?> liststudent(String username) {

        Student student = studentService.getById(username);

        DormRoom dormRoom = dormRoomService.judgeHadBed(username);
        Queqin queqin = new Queqin();

        queqin.setName(student.getName());
        queqin.setFangjianId(dormRoom.getDormRoomId());
        queqin.setLouId(dormRoom.getDormBuildId());
        queqin.setLouchengId(dormRoom.getFloorNum());

        if(dormRoom.getFirstBed()!=null)
        {
            queqin.setChuangwei("一号床位");
        }
        if(dormRoom.getSecondBed()!=null)
        {
            queqin.setChuangwei("二号床位");
        }
        if(dormRoom.getThirdBed()!=null)
        {
            queqin.setChuangwei("三号床位");
        }
        if(dormRoom.getFourthBed()!=null)
        {
            queqin.setChuangwei("四号床位");
        }
        return  R.success(queqin);
    }
    //公告更新
    @PutMapping("/update")
    public R<?> update(@RequestBody Queqin queqin) {
        int i = queqinService.updateNewNotice(queqin);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    //公告删除
    @DeleteMapping("/delete/{id}")
    public R<?> delete(@PathVariable Integer id) {
        int i = queqinService.deleteNotice(id);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }


    @GetMapping("/find")
    public R<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search) {
        Page page = queqinService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }

    @GetMapping("/finds")
    public R<?> findPages(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         String username) {
        Page page = queqinService.finds(pageNum, pageSize, username);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }
    @GetMapping("/homePageNotice")
    public R<?> homePageNotice() {
        List<?> list = queqinService.homePageNotice();
        if (list != null) {
            return R.success(list);
        } else {
            return R.error("-1", "首页公告查询失败");
        }
    }

}

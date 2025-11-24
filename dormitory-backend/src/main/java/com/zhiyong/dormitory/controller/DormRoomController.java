package com.zhiyong.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.DormRoom;
import com.zhiyong.dormitory.service.DormRoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

//房间 控制层
@RestController
@RequestMapping("/room")
public class DormRoomController {

    @Resource
    private DormRoomService dormRoomService;

    //添加房间
    @PostMapping("/add")
    public R<?> add(@RequestBody DormRoom dormRoom) {
        int i = dormRoomService.addNewRoom(dormRoom);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "添加失败");
        }
    }

    //更新房间
    @PutMapping("/update")
    public R<?> update(@RequestBody DormRoom dormRoom) {
        int i = dormRoomService.updateNewRoom(dormRoom);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    //删除房间
    @DeleteMapping("/delete/{dormRoomId}")
    public R<?> delete(@PathVariable Integer dormRoomId) {
        int i = dormRoomService.deleteRoom(dormRoomId);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }

    //查找房间
    @GetMapping("/find")
    public R<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search) {
        Page page = dormRoomService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }

    //首页顶部：空宿舍统计
    @GetMapping("/noFullRoom")
    public R<?> noFullRoom() {
        int num = dormRoomService.notFullRoom();
        if (num >= 0) {
            return R.success(num);
        } else {
            return R.error("-1", "空宿舍查询失败");
        }
    }

    //删除床位学生信息
    @DeleteMapping("/delete/{bedName}/{dormRoomId}/{calCurrentNum}")
    public R<?> deleteBedInfo(@PathVariable String bedName, @PathVariable Integer dormRoomId, @PathVariable int calCurrentNum) {
        int i = dormRoomService.deleteBedInfo(bedName, dormRoomId, calCurrentNum);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }

    //床位信息，查询该学生是否已由床位
    @GetMapping("/judgeHadBed/{value}")
    public R<?> judgeHadBed(@PathVariable String value) {
        DormRoom dormRoom = dormRoomService.judgeHadBed(value);
        if (dormRoom == null) {
            return R.success();
        } else {
            return R.error("-1", "该学生已有宿舍");
        }
    }

    //主页 住宿人数
    @GetMapping("/selectHaveRoomStuNum")
    public R<?> selectHaveRoomStuNum() {
        Long count = dormRoomService.selectHaveRoomStuNum();
        if (count >= 0) {
            return R.success(count);
        } else {
            return R.error("-1", "查询首页住宿人数失败");
        }
    }

    //住宿分布人数
    @GetMapping("/getEachBuildingStuNum/{num}")
    public R<?> getEachBuildingStuNum(@PathVariable int num) {
        ArrayList<Long> arrayList = new ArrayList();
        for (int i = 1; i <= num; i++) {
            Long eachBuildingStuNum = dormRoomService.getEachBuildingStuNum(i);
            arrayList.add(eachBuildingStuNum);
        }

        if (!arrayList.isEmpty()) {
            return R.success(arrayList);
        } else {
            return R.error("-1", "获取人数失败");
        }
    }

    //学生功能： 我的宿舍
    @GetMapping("/getMyRoom/{name}")
    public R<?> getMyRoom(@PathVariable String name) {
        DormRoom dormRoom = dormRoomService.judgeHadBed(name);
        if (dormRoom != null) {
            return R.success(dormRoom);
        } else {
            return R.error("-1", "不存在该生");
        }
    }

    //检查房间是否满员
    @GetMapping("/checkRoomState/{dormRoomId}")
    public R<?> checkRoomState(@PathVariable Integer dormRoomId) {
        DormRoom dormRoom = dormRoomService.checkRoomState(dormRoomId);
        if (dormRoom != null) {
            return R.success(dormRoom);
        } else {
            return R.error("-1", "该房间人满了");
        }
    }

    //检查床位是否已经有人
    @GetMapping("/checkBedState/{dormRoomId}/{bedNum}")
    public R<?> getMyRoom(@PathVariable Integer dormRoomId, @PathVariable int bedNum) {
        DormRoom dormRoom = dormRoomService.checkBedState(dormRoomId, bedNum);
        if (dormRoom != null) {
            return R.success(dormRoom);
        } else {
            return R.error("-1", "该床位已有人");
        }
    }

    //检查房间是否满员
    @GetMapping("/checkRoomExist/{dormRoomId}")
    public R<?> checkRoomExist(@PathVariable Integer dormRoomId) {
        DormRoom dormRoom = dormRoomService.checkRoomExist(dormRoomId);
        if (dormRoom != null) {
            return R.success(dormRoom);
        } else {
            return R.error("-1", "不存在该房间");
        }
    }
}

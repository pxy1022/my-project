package com.zhiyong.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.DormBuild;
import com.zhiyong.dormitory.service.DormBuildService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

//管理员用户 控制层
@RestController
@RequestMapping("/building")
public class DormBuildController {

    @Resource
    private DormBuildService dormBuildService;

    //楼宇添加
    @PostMapping("/add")
    public R<?> add(@RequestBody DormBuild dormBuild) {
        int i = dormBuildService.addNewBuilding(dormBuild);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "添加失败");
        }
    }

    //楼宇信息更新
    @PutMapping("/update")
    public R<?> update(@RequestBody DormBuild dormBuild) {
        int i = dormBuildService.updateNewBuilding(dormBuild);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    //楼宇删除
    @DeleteMapping("/delete/{dormBuildId}")
    public R<?> delete(@PathVariable Integer dormBuildId) {
        int i = dormBuildService.deleteBuilding(dormBuildId);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }

    //楼宇查找
    @GetMapping("/find")
    public R<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search) {
        Page page = dormBuildService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }

    //首页Echarts 获取楼宇信息
    @GetMapping("/getBuildingName")
    public R<?> getBuildingName() {
        List<DormBuild> buildingName = dormBuildService.getBuildingId();
        List<Integer> buildingId = buildingName.stream().map(dormBuildId -> dormBuildId.getDormBuildId()).collect(Collectors.toList());

        if (!buildingId.isEmpty()) {
            return R.success(buildingId);
        } else {
            return R.error("-1", "查询失败");
        }

    }
}

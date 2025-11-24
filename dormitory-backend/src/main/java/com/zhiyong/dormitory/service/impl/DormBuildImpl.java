package com.zhiyong.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyong.dormitory.entity.DormBuild;
import com.zhiyong.dormitory.mapper.DormBuildMapper;
import com.zhiyong.dormitory.service.DormBuildService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DormBuildImpl extends ServiceImpl<DormBuildMapper, DormBuild> implements DormBuildService {

    @Resource
    private DormBuildMapper dormBuildMapper;

    /**
     * 楼宇添加
     */
    @Override
    public int addNewBuilding(DormBuild dormBuild) {
        return dormBuildMapper.insert(dormBuild);
    }

    /**
     * 楼宇查找
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<DormBuild> qw = new QueryWrapper<>();
        qw.like("DormBuild_id", search);
        return dormBuildMapper.selectPage(page, qw);
    }

    /**
     * 楼宇信息更新
     */
    @Override
    public int updateNewBuilding(DormBuild dormBuild) {
        return dormBuildMapper.updateById(dormBuild);
    }

    /**
     * 楼宇删除
     */
    @Override
    public int deleteBuilding(Integer id) {
        return dormBuildMapper.deleteById(id);
    }

    /**
     * 首页 获取建筑名称
     */
    @Override
    public List<DormBuild> getBuildingId() {
        QueryWrapper<DormBuild> qw = new QueryWrapper<>();
        qw.select("dormbuild_id");
        return dormBuildMapper.selectList(qw);
    }

}

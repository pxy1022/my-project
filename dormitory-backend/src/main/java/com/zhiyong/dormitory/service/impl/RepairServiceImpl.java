package com.zhiyong.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyong.dormitory.entity.Repair;
import com.zhiyong.dormitory.mapper.RepairMapper;
import com.zhiyong.dormitory.service.RepairService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

    @Resource
    private RepairMapper repairMapper;

    /**
     * 添加订单
     */
    @Override
    public int addNewOrder(Repair repair) {
        return repairMapper.insert(repair);
    }

    /**
     * 查找订单
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<Repair> qw = new QueryWrapper<>();
        qw.like("title", search);
        return repairMapper.selectPage(page, qw);
    }

    @Override
    public Page individualFind(Integer pageNum, Integer pageSize, String search, String name) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<Repair> qw = new QueryWrapper<>();
        qw.like("title", search);
        qw.eq("repairer", name);
        return repairMapper.selectPage(page, qw);
    }

    /**
     * 更新订单
     */
    @Override
    public int updateNewOrder(Repair repair) {
        return repairMapper.updateById(repair);
    }

    /**
     * 删除订单
     */
    @Override
    public int deleteOrder(Integer id) {
        return repairMapper.deleteById(id);
    }

    /**
     * 首页顶部：报修统计
     */
    @Override
    public int showOrderNum() {
        QueryWrapper<Repair> qw = new QueryWrapper<>();
        return Math.toIntExact(repairMapper.selectCount(qw));
    }
}

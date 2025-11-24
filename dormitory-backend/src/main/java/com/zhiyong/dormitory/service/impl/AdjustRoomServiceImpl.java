package com.zhiyong.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyong.dormitory.entity.AdjustRoom;
import com.zhiyong.dormitory.mapper.AdjustRoomMapper;
import com.zhiyong.dormitory.service.AdjustRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdjustRoomServiceImpl extends ServiceImpl<AdjustRoomMapper, AdjustRoom> implements AdjustRoomService {

    @Resource
    private AdjustRoomMapper adjustRoomMapper;

    /**
     * 添加调宿申请
     */
    @Override
    public int addApply(AdjustRoom adjustRoom) {
        return adjustRoomMapper.insert(adjustRoom);
    }

    /**
     * 查找调宿申请
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<AdjustRoom> qw = new QueryWrapper<>();
        qw.like("username", search);
        return adjustRoomMapper.selectPage(page, qw);
    }

    /**
     * 删除调宿申请
     */
    @Override
    public int deleteAdjustment(Integer id) {
        return adjustRoomMapper.deleteById(id);
    }


    /**
     * 更新调宿申请
     */
    @Override
    public int updateApply(AdjustRoom adjustRoom) {
        return adjustRoomMapper.updateById(adjustRoom);
    }

}

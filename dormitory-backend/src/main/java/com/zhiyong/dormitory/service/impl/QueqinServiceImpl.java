package com.zhiyong.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyong.dormitory.entity.Notice;
import com.zhiyong.dormitory.entity.Queqin;
import com.zhiyong.dormitory.mapper.NoticeMapper;
import com.zhiyong.dormitory.mapper.QueqinMapper;
import com.zhiyong.dormitory.service.NoticeService;
import com.zhiyong.dormitory.service.QueqinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class QueqinServiceImpl extends ServiceImpl<QueqinMapper, Queqin> implements QueqinService {

    @Resource
    private QueqinMapper queqinMapper;

    /**
     * 公告添加
     */
    @Override
    public int addNewNotice(Queqin notice) {
        return queqinMapper.insert(notice);
    }

    /**
     * 公告查找
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<Queqin> qw = new QueryWrapper<>();
        qw.like("username", search);
        return queqinMapper.selectPage(page, qw);
    }

    /**
     * 公告更新
     */
    @Override
    public int updateNewNotice(Queqin notice) {
        return queqinMapper.updateById(notice);
    }

    /**
     * 公告删除
     */
    @Override
    public int deleteNotice(Integer id) {
        return queqinMapper.deleteById(id);
    }

    /**
     * 首页公告展示
     */
    @Override
    public List<?> homePageNotice() {
        QueryWrapper<Queqin> qw = new QueryWrapper<>();
        qw.orderByDesc("release_time");
        return queqinMapper.selectList(qw);
    }

    @Override
    public Page finds(Integer pageNum, Integer pageSize, String username) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<Queqin> qw = new QueryWrapper<>();
        qw.eq("username", username);
        System.out.println(username);
        return queqinMapper.selectPage(page, qw);
    }
}

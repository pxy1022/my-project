package com.zhiyong.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyong.dormitory.entity.Notice;
import com.zhiyong.dormitory.mapper.NoticeMapper;
import com.zhiyong.dormitory.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    /**
     * 公告添加
     */
    @Override
    public int addNewNotice(Notice notice) {
        return noticeMapper.insert(notice);
    }

    /**
     * 公告查找
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<Notice> qw = new QueryWrapper<>();
        qw.like("title", search);
        return noticeMapper.selectPage(page, qw);
    }

    /**
     * 公告更新
     */
    @Override
    public int updateNewNotice(Notice notice) {
        return noticeMapper.updateById(notice);
    }

    /**
     * 公告删除
     */
    @Override
    public int deleteNotice(Integer id) {
        return noticeMapper.deleteById(id);
    }

    /**
     * 首页公告展示
     */
    @Override
    public List<?> homePageNotice() {
        QueryWrapper<Notice> qw = new QueryWrapper<>();
        qw.orderByDesc("release_time");
        return noticeMapper.selectList(qw);
    }
}

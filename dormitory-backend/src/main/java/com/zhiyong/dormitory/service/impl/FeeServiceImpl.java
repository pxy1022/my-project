package com.zhiyong.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyong.dormitory.entity.Fee;
import com.zhiyong.dormitory.mapper.FeeMapper;
import com.zhiyong.dormitory.service.FeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class FeeServiceImpl extends ServiceImpl<FeeMapper, Fee> implements FeeService {

    @Resource
    private FeeMapper feeMapper;

    /**
     * 费用添加
     */
    @Override
    public int addNewFee(Fee fee) {
        return feeMapper.insert(fee);
    }

    /**
     * 费用查询
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<Fee> qw = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            qw.like("fee_type", search)
                    .or().like("dormbuild_id", search)
                    .or().like("dormroom_id", search);
        }
        return feeMapper.selectPage(page, qw);
    }

    /**
     * 费用信息更新
     */
    @Override
    public int updateNewFee(Fee fee) {
        return feeMapper.updateById(fee);
    }

    /**
     * 费用删除
     */
    @Override
    public int deleteFee(Integer id) {
        return feeMapper.deleteById(id);
    }

}


package com.zhiyong.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyong.dormitory.entity.Student;
import com.zhiyong.dormitory.mapper.StudentMapper;
import com.zhiyong.dormitory.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    /**
     * 学生登陆
     */
    @Override
    public Student stuLogin(String username, String password) {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("username", username);
        qw.eq("password", password);
        Student student = studentMapper.selectOne(qw);
        if (student != null) {
            return student;
        } else {
            return null;
        }
    }

    /**
     * 学生新增
     */
    @Override
    public int addNewStudent(Student student) {
        return studentMapper.insert(student);
    }

    /**
     * 分页查询学生
     */
    @Override
    public Page find(Integer pageNum, Integer pageSize, String search) {
        Page page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.like("name", search);
        return studentMapper.selectPage(page, qw);
    }

    /**
     * 更新学生信息
     */
    @Override
    public int updateNewStudent(Student student) {
        return studentMapper.updateById(student);
    }

    /**
     * 删除学生信息
     */
    @Override
    public int deleteStudent(String username) {
        return studentMapper.deleteById(username);
    }


    /**
     * 主页顶部：学生统计
     */
    @Override
    public int stuNum() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.isNotNull("username");
        return Math.toIntExact(studentMapper.selectCount(qw));
    }

    /**
     * 床位信息，查询该学生信息
     */
    @Override
    public Student stuInfo(String username) {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.eq("username", username);
        return studentMapper.selectOne(qw);
    }

}

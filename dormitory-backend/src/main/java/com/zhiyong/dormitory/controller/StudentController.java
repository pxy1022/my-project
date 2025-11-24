package com.zhiyong.dormitory.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.entity.Student;
import com.zhiyong.dormitory.entity.User;
import com.zhiyong.dormitory.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

//学生 控制层
@RestController
@RequestMapping("/stu")
public class StudentController {

    @Resource
    private StudentService studentService;

    //添加学生信息
    @PostMapping("/add")
    public R<?> add(@RequestBody Student student) {
        int i = studentService.addNewStudent(student);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "添加失败");
        }

    }

    //更新学生信息
    @PutMapping("/update")
    public R<?> update(@RequestBody Student student) {
        int i = studentService.updateNewStudent(student);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "更新失败");
        }
    }

    //删除学生信息
    @DeleteMapping("/delete/{username}")
    public R<?> delete(@PathVariable String username) {
        int i = studentService.deleteStudent(username);
        if (i == 1) {
            return R.success();
        } else {
            return R.error("-1", "删除失败");
        }
    }

    //查找学生信息
    @GetMapping("/find")
    public R<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") String search) {
        Page page = studentService.find(pageNum, pageSize, search);
        if (page != null) {
            return R.success(page);
        } else {
            return R.error("-1", "查询失败");
        }
    }

    //学生登录
    @PostMapping("/login")
    public R<?> login(@RequestBody User user, HttpSession session) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        Object o = studentService.stuLogin(user.getUsername(), user.getPassword());
        if (o != null) {
            System.out.println(o);
            //存入session
            session.setAttribute("Identity", "stu");
            session.setAttribute("User", o);
            return R.success(o);
        } else {
            return R.error("-1", "用户名或密码错误");
        }
    }

    //主页顶部：学生统计
    @GetMapping("/stuNum")
    public R<?> stuNum() {
        int num = studentService.stuNum();
        if (num > 0) {
            return R.success(num);
        } else {
            return R.error("-1", "查询失败");
        }
    }


    //床位信息，查询是否存在该学生
    // 床位信息，查询床位上的学生信息
    @GetMapping("/exist/{value}")
    public R<?> exist(@PathVariable String value) {
        Student student = studentService.stuInfo(value);
        if (student != null) {
            return R.success(student);
        } else {
            return R.error("-1", "不存在该学生");
        }
    }
}

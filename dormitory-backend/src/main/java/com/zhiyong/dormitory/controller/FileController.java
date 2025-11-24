package com.zhiyong.dormitory.controller;

import cn.hutool.core.io.FileUtil;
import com.zhiyong.dormitory.common.R;
import com.zhiyong.dormitory.common.UID;
import com.zhiyong.dormitory.entity.Admin;
import com.zhiyong.dormitory.entity.DormManager;
import com.zhiyong.dormitory.entity.Student;
import com.zhiyong.dormitory.service.AdminService;
import com.zhiyong.dormitory.service.DormManagerService;
import com.zhiyong.dormitory.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
//import java.util.Base64;
import sun.misc.BASE64Encoder;

// 文件相关 控制层
@RestController
@RequestMapping("/files")
public class FileController {

    private static String rootFilePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/";
    private static String originalFilename = "";

    @Resource
    private StudentService studentService;

    @Resource
    private AdminService adminService;

    @Resource
    private DormManagerService dormManagerService;

    // 将上传的头像写入本地 rootFilePath
    @PostMapping("/upload")
    public R<?> upload(MultipartFile file) throws IOException {
        // 获取文件名
        originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        // 获取文件尾缀
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());

        // 重命名
        String uid = new UID().produceUID();
        originalFilename = uid + fileType;
        System.out.println(originalFilename);
        // 存储位置
        String targetPath = rootFilePath + originalFilename;
        System.out.println(targetPath);
        // 获取字节流
        FileUtil.writeBytes(file.getBytes(), targetPath);

        return R.success("上传成功");
    }

    // 将头像名称更新到数据库中
    @PostMapping("/uploadAvatar/stu")
    public R<?> uploadStuAvatar(@RequestBody Student student) {
        if (originalFilename!= null) {
            student.setAvatar(originalFilename);
            System.out.println(student);
            int i = studentService.updateNewStudent(student);
            if (i == 1) {
                return R.success(originalFilename);
            }
        } else {
            return R.error("-1", "rootFilePath为空");
        }
        return R.error("-1", "设置头像失败");
    }

    @PostMapping("/uploadAvatar/admin")
    public R<?> uploadAdminAvatar(@RequestBody Admin admin) {
        if (originalFilename!= null) {
            admin.setAvatar(originalFilename);
            int i = adminService.updateAdmin(admin);
            if (i == 1) {
                return R.success(originalFilename);
            }
        } else {
            return R.error("-1", "rootFilePath为空");
        }
        return R.error("-1", "设置头像失败");
    }

    @PostMapping("/uploadAvatar/dormManager")
    public R<?> uploadDormManagerAvatar(@RequestBody DormManager dormManager) {
        if (originalFilename!= null) {
            dormManager.setAvatar(originalFilename);
            int i = dormManagerService.updateNewDormManager(dormManager);
            if (i == 1) {
                return R.success(originalFilename);
            }
        } else {
            return R.error("-1", "rootFilePath为空");
        }
        return R.error("-1", "设置头像失败");
    }

    // 前端调用接口，后端查询存储与本地的头像，进行 Base64 编码 发送到前端
    @GetMapping("/initAvatar/{filename}")
    public R<?> initAvatar(@PathVariable String filename) throws IOException {
        System.out.println(filename);
        String path = rootFilePath + filename;
        System.out.println(path);
        return R.success(getImage(path));
    }

    private R<?> getImage(String path) throws IOException {

        // 读取图片变成字节数组
        FileInputStream fileInputStream = new FileInputStream(path);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = fileInputStream.read(b))!= -1) {
            bos.write(b, 0, len);
        }
        byte[] fileByte = bos.toByteArray();

        // 进行 base64 编码
//        String data = Base64.getEncoder().encodeToString(fileByte);
//
//        fileInputStream.close();
//        bos.close();
//        return R.success(data);
        BASE64Encoder encoder = new BASE64Encoder();
        String data = encoder.encode(fileByte);

        fileInputStream.close();
        bos.close();
        return R.success(data);
    }

}
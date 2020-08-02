package cn.yhs.learn.controller;

import cn.yhs.learn.domain.Department;
import cn.yhs.learn.domain.ResultData;
import cn.yhs.learn.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.controller.DepartmentController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/6 15:44
 * @Description: todo
 **/
@RestController
@RequestMapping("/dept")
public class DepartmentController {


    @Autowired
    public DepartmentService departmentService;

    @GetMapping("/findAll")
    public ResultData findAll() {
        List<Department> departmentList = departmentService.findAll();
        return ResultData.SUCCESS(departmentList);
    }

}

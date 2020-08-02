package cn.yhs.learn.service;

import cn.yhs.learn.domain.Department;

import java.util.List;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.service.DepartmentService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/6 15:46
 * @Description: todo
 **/
public interface DepartmentService {
    List<Department> findAll();
}

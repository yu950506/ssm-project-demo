package cn.yhs.learn.service;

import cn.yhs.learn.domain.Employee;

import java.util.List;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.service.EmplyeeService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/4 15:02
 * @Description: todo
 **/
public interface EmployeeService {

    void save(Employee employee);

    List<Employee> findAll();

    Employee findByEmpId(Integer empId);

    void updateEmp(Employee employee);

    void deleteEmpById(Integer empId);

    void deleteAllIds(Integer[] ids);
}

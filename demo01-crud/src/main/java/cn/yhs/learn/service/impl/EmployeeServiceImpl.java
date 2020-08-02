package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.EmployeeDao;
import cn.yhs.learn.domain.Employee;
import cn.yhs.learn.service.EmployeeService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.service.impl.EmployeeServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/4 15:03
 * @Description: todo
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    // 用于批量删除的session
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findByEmpId(Integer empId) {
        return employeeDao.findByEmpId(empId);
    }

    @Override
    public void updateEmp(Employee employee) {
        employeeDao.updateEmp(employee);
    }

    @Override
    public void deleteEmpById(Integer empId) {
        employeeDao.deleteEmpById(empId);
    }

    @Override
    public void deleteAllIds(Integer[] ids) {
        // 批量删除
        EmployeeDao employeeDao = sqlSessionTemplate.getMapper(EmployeeDao.class);
        for (Integer id : ids) {
            employeeDao.deleteEmpById(id);
        }
    }
}

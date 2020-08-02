package cn.yhs.learn.test;

import cn.yhs.learn.dao.DepartmentDao;
import cn.yhs.learn.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.test.DaoTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/4 14:52
 * @Description: todo
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testEmpSave() {
        System.out.println(employeeService);
        System.out.println(employeeService.getClass());

    }

}

package cn.yhs.learn.test;

import cn.yhs.learn.dao.DepartmentDao;
import cn.yhs.learn.dao.EmployeeDao;
import cn.yhs.learn.domain.Department;
import cn.yhs.learn.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
public class DaoTest {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testDeptSave() {
        //departmentDao.save(new Department(null, "开发部", "开发人员的部门"));
        // departmentDao.save(new Department(null, "研发部", "研发人员的部门"));
        // departmentDao.save(new Department(null, "测试部", "测试人员的部门"));
        Connection connection = sqlSessionTemplate.getConnection();
        System.out.println("connection = " + connection);
    }

    /**
     * 测试批量插入
     */
    @Test
    public void testEmpSave() {
        // 使用批量的SqlSession获取Mapper
        EmployeeDao employeeDao = sqlSessionTemplate.getMapper(EmployeeDao.class);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String name = UUID.randomUUID().toString().substring(0, 5);
            if (i % 3 == 0)
                employeeDao.save(new Employee(null, name + i, 'M', name + "@163.com", new Date(), 1, null));
            if (i % 3 == 1)
                employeeDao.save(new Employee(null, name + i, 'F', name + "@126.com", new Date(), 2, null));
            if (i % 3 == 2)
                employeeDao.save(new Employee(null, name + i, 'M', name + "@qq.com", new Date(), 3, null));
        }
        //耗时 = 3863 ms
        System.out.println("耗时 = " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void testEmpFind() {
        List<Employee> all = employeeDao.findAll();
        for (int i = 0; i < 10; i++) {
            Employee employee = all.get(i);
            System.out.println(employee);
        }
    }
}

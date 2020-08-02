package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.dao.EmployeeDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/4 14:48
 * @Description: todo
 **/
@Repository
public interface EmployeeDao {
    /**
     * 插入一个员工不带部门信息的
     *
     * @param employee
     */
    @Insert("insert into employee (name,gender,email,birthday,dept_id) value (#{name},#{gender},#{email},#{birthday},#{deptId})")
    void save(Employee employee);


    @Select("select * from employee")
    @Results(id = "empWithDept", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "dept_id", property = "department", one = @One(select = "cn.yhs.learn.dao.DepartmentDao.findByDeptId"))
    })
    List<Employee> findAll();

    @Select("select * from employee where id = #{empId}")
    @ResultMap("empWithDept")
    Employee findByEmpId(Integer empId);

    @Update("update employee set name =#{name},gender = #{gender},email = #{email},birthday = #{birthday},dept_id = #{deptId} where id = #{id}")
    void updateEmp(Employee employee);

    @Delete("delete from employee where id = #{empId}")
    void deleteEmpById(Integer empId);
}

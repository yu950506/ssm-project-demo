package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Department;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.dao.DepartmentDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/4 14:46
 * @Description: 部门dao
 **/
@Repository
public interface DepartmentDao {

    // 踩坑1：不要用mysql的关键字做字段，不要会出问题 desc => description
    @Insert("insert into department(name,description) value(#{name},#{description})")
    void save(Department department);

    @Select("select * from department where id = #{deptId}")
    Department findByDeptId(Integer deptId);

    @Select("select * from department")
    List<Department> findAll();
}

package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.dao.RoleDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 15:40
 * @Description: todo
 **/
@Repository
public interface RoleDao {
    /**
     * 查询所有角色,并附带该角色所有的权限
     *
     * @return
     */
    @Select("select * from role")
    @Results(id = "role", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "id", property = "permissionList", many = @Many(select = "cn.yhs.learn.dao.PermissionDao.findAllByRoleId"))
    })
    List<Role> findAllWithPermission();

    /**
     * 根据用户id查询该用户拥有的所有角色
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id in(select role_id from user_role where user_id = #{userId})")
    @ResultMap("role")
    List<Role> findAllWithPermissionByUserId(Integer userId);

    /**
     * 根据角色id删除角色
     *
     * @param id
     */
    @Delete("delete from role where id = #{id}")
    void deleteRoleById(Integer id);

    /**
     * 添加一条角色数据
     *
     * @param role
     */
    @Insert("insert into role(role_name,role_desc) value (#{roleName},#{roleDesc})")
    void addRole(Role role);

    /**
     * 更新角色数据
     *
     * @param role
     */
    @Update("update role set role_name = #{roleName},role_desc = #{roleDesc} where id = #{id}")
    void updateRole(Role role);
}

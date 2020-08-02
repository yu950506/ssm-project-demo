package cn.yhs.learn.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.dao.UserRoleDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/26 8:27
 * @Description: todo
 **/
@Repository
public interface UserRoleDao {
    /**
     * 根据用户id删除所关联的角色
     *
     * @param userId 用户id
     */
    @Delete("delete from user_role where user_id = #{userId}")
    void deleteByUserId(Integer userId);

    /**
     * 根据角色id删除该角色绑定的用户
     *
     * @param roleId 角色id
     */
    @Delete("delete from user_role where role_id = #{roleId}")
    void deleteByRoleId(Integer roleId);

    /**
     * 给用户添加角色信息
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Insert("insert into user_role(user_id,role_id) value(#{userId},#{roleId})")
    void addRoleForUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 给用户删除角色信息
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Delete("delete from user_role where user_id = #{userId} and role_id = #{roleId}")
    void deleteRoleForUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

}

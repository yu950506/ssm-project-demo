package cn.yhs.learn.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.dao.RolePermissionDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/25 14:03
 * @Description: todo
 **/
@Repository
public interface RolePermissionDao {
    /**
     * 根据角色id,删除该角色拥有的所有权限
     *
     * @param roleId 角色id
     */
    @Delete("delete from role_permission where role_id = #{roleId}")
    void deleteByRoleId(Integer roleId);

    /**
     * 根据权限id删除该权限id关联的角色
     *
     * @param permissionId 权限id
     */
    @Delete("delete from role_permission where permission_id = #{permissionId}")
    void deleteByPermissionId(Integer permissionId);

    /**
     * 为指定角色分配指定权限
     *
     * @param roleId       角色id
     * @param permissionId 权限id
     */
    @Insert("insert into role_permission(role_id,permission_id) value (#{roleId},#{permissionId})")
    void assignPermissionForRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
    // todo bug:org.apache.ibatis.binding.BindingException: Parameter 'roleId' not found. Available parameters are [arg1, arg0, param1, param2]

    /**
     * 为指定角色删除指定权限
     *
     * @param roleId       角色id
     * @param permissionId 权限id
     */
    @Delete("delete from role_permission where role_id = #{roleId} and permission_id = #{permissionId}")
    void deletePermissionForRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

}

package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.dao.PermissionDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 15:30
 * @Description: todo
 **/
@Repository
public interface PermissionDao {
    /**
     * 查询所有权限数据
     *
     * @return
     */
    @Select("select * from permission")
    @Results(id = "permission", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "permission_name", property = "permissionName"),
            @Result(column = "permission_url", property = "permissionUrl"),
            @Result(column = "permission_desc", property = "permissionDesc")
    })
    List<Permission> findAll();

    /**
     * 根据角色id查询该角色拥有的所有权限
     *
     * @param roleId
     * @return
     */
    @Select("select * from permission where id in(select permission_id from role_permission where role_id = #{roleId})")
    @ResultMap("permission")
    // 引用上面的 id=permission 结果集
    List<Permission> findAllByRoleId(Integer roleId);

    /**
     * 新增一条权限数据
     *
     * @param permission
     */
    @Insert("insert into permission(permission_name,permission_url,permission_desc) value (#{permissionName},#{permissionUrl},#{permissionDesc})")
    void addPermission(Permission permission);

    /**
     * 根据权限id删除权限
     *
     * @param id
     */
    @Delete("delete from permission where id = #{id}")
    void deletePermissionById(Integer id);

    /**
     * 更新权限数据
     *
     * @param permission
     */
    @Update("update permission set permission_name = #{permissionName},permission_url = #{permissionUrl},permission_desc = #{permissionDesc} where id = #{id}")
    void updatePermission(Permission permission);
}

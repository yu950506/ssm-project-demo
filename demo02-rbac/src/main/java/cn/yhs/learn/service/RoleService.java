package cn.yhs.learn.service;

import cn.yhs.learn.domain.Role;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.service.RoleService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/24 10:11
 * @Description: todo
 **/
public interface RoleService {
    List<Role> findAll();

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRoleById(Integer id);

    void deleteRoleByIds(Integer[] ids);

    void deleteAllPermissionForRole(Integer roleId, Integer[] permissionIds);

    void assignAllPermissionForRole(Integer roleId, Integer[] permissionIds);
}

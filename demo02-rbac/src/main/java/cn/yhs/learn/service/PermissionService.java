package cn.yhs.learn.service;

import cn.yhs.learn.domain.Permission;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.service.PermissionService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/24 10:15
 * @Description: todo
 **/
public interface PermissionService {
    List<Permission> findAll();

    void addPermission(Permission permission);

    void deletePermissionById(Integer id);

    void updatePermission(Permission permission);

    void deletePermissionByIds(Integer[] ids);
}

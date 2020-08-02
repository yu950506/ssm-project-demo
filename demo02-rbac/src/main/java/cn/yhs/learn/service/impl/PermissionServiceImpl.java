package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.PermissionDao;
import cn.yhs.learn.dao.RolePermissionDao;
import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.service.PermissionService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.service.impl.PermissionServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/24 10:16
 * @Description: todo
 **/
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void addPermission(Permission permission) {
        permissionDao.addPermission(permission);
    }

    @Override
    public void deletePermissionById(Integer id) {
        // todo 删除角色权限中间的表的数据
        // 1. 先删除角色权限中间表的数据
        rolePermissionDao.deleteByPermissionId(id);
        // 2. 在删除权限表的数据
        permissionDao.deletePermissionById(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
    }

    @Override
    public void deletePermissionByIds(Integer[] ids) {
        PermissionDao permissionMapper = sqlSessionTemplate.getMapper(PermissionDao.class);
        RolePermissionDao rolePermissionMapper = sqlSessionTemplate.getMapper(RolePermissionDao.class);
        for (Integer id : ids) {
            rolePermissionMapper.deleteByPermissionId(id);
            permissionMapper.deletePermissionById(id);
        }
    }
}

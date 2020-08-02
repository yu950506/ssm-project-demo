package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.RoleDao;
import cn.yhs.learn.dao.RolePermissionDao;
import cn.yhs.learn.dao.UserRoleDao;
import cn.yhs.learn.domain.Role;
import cn.yhs.learn.domain.RolePermission;
import cn.yhs.learn.service.RoleService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.service.impl.RoleServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/24 10:11
 * @Description: todo
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Role> findAll() {
        return roleDao.findAllWithPermission();
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    /**
     * 角色删除：涉及到三张表的删除
     *
     * @param id 角色id
     */
    @Override
    public void deleteRoleById(Integer id) {
        // 1.删除角色权限关联表的数据
        rolePermissionDao.deleteByRoleId(id);
        // 2. 删除用户角色关联表的数据
        userRoleDao.deleteByRoleId(id);
        // 3.删除角色表中的数据
        roleDao.deleteRoleById(id);

    }

    /**
     * 批量删除角色
     *
     * @param ids 角色id数组
     */
    @Override
    public void deleteRoleByIds(Integer[] ids) {
        RoleDao roleMapper = sqlSessionTemplate.getMapper(RoleDao.class);
        RolePermissionDao rolePermissionMapper = sqlSessionTemplate.getMapper(RolePermissionDao.class);
        UserRoleDao userRoleMapper = sqlSessionTemplate.getMapper(UserRoleDao.class);
        for (Integer id : ids) {
            // 先删除角色权限中间表的数据，因为有外键引用
            rolePermissionMapper.deleteByRoleId(id);
            // 删除用户角色中间表的数据
            userRoleMapper.deleteByRoleId(id);
            // 再删除角色表
            roleMapper.deleteRoleById(id);
        }
    }

    /**
     * 为指定角色删除指定的权限
     *
     * @param roleId        角色Id
     * @param permissionIds 权限id数组
     */
    @Override
    public void deleteAllPermissionForRole(Integer roleId, Integer[] permissionIds) {
        RolePermissionDao rolePermissionMapper = sqlSessionTemplate.getMapper(RolePermissionDao.class);
        for (Integer permissionId : permissionIds) {
            rolePermissionMapper.deletePermissionForRole(roleId, permissionId);
        }
    }

    /**
     * 批量为角色删除权限
     *
     * @param roleId        角色id
     * @param permissionIds 权限id数组
     */
    @Override
    public void assignAllPermissionForRole(Integer roleId, Integer[] permissionIds) {
        RolePermissionDao rolePermissionMapper = sqlSessionTemplate.getMapper(RolePermissionDao.class);
        for (Integer permissionId : permissionIds) {
            rolePermissionMapper.assignPermissionForRole(roleId, permissionId);
        }
    }
}

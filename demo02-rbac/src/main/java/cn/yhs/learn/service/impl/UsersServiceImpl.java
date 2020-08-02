package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.UserDao;
import cn.yhs.learn.dao.UserRoleDao;
import cn.yhs.learn.domain.Users;
import cn.yhs.learn.service.UsersService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.service.impl.UsersServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 20:23
 * @Description: 用户控制
 **/
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Users> findAll() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(Users users) {
        userDao.saveUser(users);
    }

    /**
     * 根据用户id删除用户信息，也要删除用户角色中间表色数据
     *
     * @param id 用户id
     */
    @Override
    public void deleteByUserId(Integer id) {
        // 1. 先删除用户角色表中的数据
        userRoleDao.deleteByUserId(id);
        // 2. 再删除用户表中的数据
        userDao.deleteByUserId(id);
    }

    /**
     * 批量删除用户，也涉及到两张表的数据删除
     *
     * @param ids 用户id数组
     */
    @Override
    public void deleteAllIds(Integer[] ids) {
        // 批量删除
        UserDao userMapper = sqlSessionTemplate.getMapper(UserDao.class);
        UserRoleDao userRoleMapper = sqlSessionTemplate.getMapper(UserRoleDao.class);
        for (Integer id : ids) {
            userRoleMapper.deleteByUserId(id);
            userMapper.deleteByUserId(id);
        }
    }

    /**
     * 批量为用户添加角色
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     */
    public void addRoleForUser(Integer userId, Integer[] roleIds) {
        UserRoleDao userRoleMapper = sqlSessionTemplate.getMapper(UserRoleDao.class);
        for (Integer roleId : roleIds) {
            userRoleMapper.addRoleForUser(userId, roleId);
        }
    }

    /**
     * 批量为用户删除角色
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     */
    public void deleteRoleForUser(Integer userId, Integer[] roleIds) {
        UserRoleDao userRoleMapper = sqlSessionTemplate.getMapper(UserRoleDao.class);
        for (Integer roleId : roleIds) {
            userRoleMapper.deleteRoleForUser(userId, roleId);
        }
    }

    @Override
    public void updateUser(Users users) {
        userDao.updateUser(users);
    }

    @Override
    public Users userLogin(Users users) {
        return userDao.userLogin(users);
    }

    @Override
    public Users findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}

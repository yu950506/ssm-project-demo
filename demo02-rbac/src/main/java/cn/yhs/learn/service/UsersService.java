package cn.yhs.learn.service;

import cn.yhs.learn.dao.UserRoleDao;
import cn.yhs.learn.domain.Users;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.service.UsersService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 20:22
 * @Description: todo
 **/
public interface UsersService {

    List<Users> findAll();

    void saveUser(Users users);

    void deleteByUserId(Integer id);

    void deleteAllIds(Integer[] ids);

    /**
     * 批量为用户添加角色
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     */
    void addRoleForUser(Integer userId, Integer[] roleIds);

    /**
     * 批量为用户删除角色
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     */
    void deleteRoleForUser(Integer userId, Integer[] roleIds);

    void updateUser(Users users);

    Users userLogin(Users users);

    Users findUserByUsername(String username);
}

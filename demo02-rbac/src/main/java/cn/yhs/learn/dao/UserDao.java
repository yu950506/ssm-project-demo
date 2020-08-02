package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.dao.UserDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 19:52
 * @Description: todo
 **/
@Repository
public interface UserDao {
    /**
     * 查询所有用户，附带用户所有的角色，以及该角色拥有的所有的权限
     *
     * @return
     */
    @Select("select * from user")
    @Results(id = "users", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "email", column = "email"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "roleList", column = "id", many = @Many(select = "cn.yhs.learn.dao.RoleDao.findAllWithPermissionByUserId")),
    })
    List<Users> findAll();

    /**
     * 插入一个新用户
     *
     * @param users
     */
    @Insert("insert into user(username,password,gender,email,create_time,status) value (#{username},#{password},#{gender},#{email},#{createTime},#{status}) ")
    void saveUser(Users users);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteByUserId(Integer id);

    /**
     * 更新用户数据
     *
     * @param users
     */
    @Update("update user set username = #{username},password = #{password},gender = #{gender},email = #{email},update_time=#{updateTime},status = #{status} where id = #{id}")
    void updateUser(Users users);

    /**
     * 用户登录的功能
     *
     * @param users 前端填写的用户登录的信息
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    Users userLogin(Users users);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    Users findUserByUsername(String username);
}

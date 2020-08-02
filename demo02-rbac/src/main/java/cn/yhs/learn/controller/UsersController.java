package cn.yhs.learn.controller;

import cn.yhs.learn.domain.ResultData;
import cn.yhs.learn.domain.Users;
import cn.yhs.learn.service.UsersService;
import cn.yhs.learn.util.PasswordUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.controller.UsersController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 20:29
 * @Description: todo
 **/
@Controller
@Slf4j
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 分页查询所有用户，默认查第一页，每页显示10条数据
     *
     * @param pageNum  当前页码数
     * @param pageSize 每页显示的数量
     * @return 统一返回API格式数据
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResultData findAllUsers(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Users> usersList = usersService.findAll();
        if (usersList != null && usersList.size() > 0) {
            PageInfo<Users> usersPageInfo = PageInfo.of(usersList);
            return ResultData.SUCCESS(new Long(usersList.size()), "成功", usersPageInfo);
        } else
            return ResultData.FAIL();
    }

    /**
     * 添加一个用户数据
     * 创建时间由后台自动添加
     * 状态也是默认的开启
     *
     * @param users 封装的用户数据
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultData addUser(Users users) {
        // 为用户设置创建时间
        users.setCreateTime(new Date());
        // 对密码进行加密
        users.setPassword(PasswordUtils.shiroMd5(users.getPassword()));
        log.info("前台传递过来的添加用户的参数：{}", users);
        usersService.saveUser(users);
        return ResultData.SUCCESS();
    }

    /**
     * 根据用户id删除用户数据
     *
     * @param id 用户id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteByUserId", method = RequestMethod.POST)
    public ResultData deleteByUserId(Integer id) {
        // 1. 首先删除用户角色关联的表中的数据，在service层进行实现
        // 2. 再删除用户表中的数据
        usersService.deleteByUserId(id);
        return ResultData.SUCCESS();
    }

    /**
     * 根据用户id数组,批量删除用户数据
     *
     * @param ids 用户id数组
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAllIds", method = RequestMethod.POST)
    public ResultData deleteAllIds(Integer[] ids) {
        usersService.deleteAllIds(ids);
        return ResultData.SUCCESS();
    }

    /**
     * 为用户分配角色
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addRoleForUser", method = RequestMethod.POST)
    public ResultData addRoleForUser(Integer userId, Integer[] roleIds) {
        usersService.addRoleForUser(userId, roleIds);
        return ResultData.SUCCESS();
    }

    /**
     * 为用户移除角色
     *
     * @param userId  用户id
     * @param roleIds 角色id数组
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteRoleForUser", method = RequestMethod.POST)
    public ResultData deleteRoleForUser(Integer userId, Integer[] roleIds) {
        usersService.deleteRoleForUser(userId, roleIds);
        return ResultData.SUCCESS();
    }

    /**
     * 更新用户的操作
     *
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResultData updateUser(Users users) {
        // 更新时间由后台进行设置
        users.setUpdateTime(new Date());
        usersService.updateUser(users);
        return ResultData.SUCCESS();
    }

    /**
     * 用户登录的验证
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultData userLogin(@RequestParam(required = true, name = "username") String username,
                                @RequestParam(required = true, name = "password") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            // 登录成功，将用户信息保存到session对象中去
            Users users = (Users) subject.getPrincipal();
            log.info("shiro认证成功之后的用户数据：{}", users);
            HttpSession session = request.getSession();
            session.setAttribute("users", username);
            return ResultData.SUCCESS();
        } catch (AuthenticationException e) { // 有异常，说明登录失败
            return ResultData.FAIL(e.getMessage());
        }
    }
}

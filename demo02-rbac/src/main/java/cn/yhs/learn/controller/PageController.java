package cn.yhs.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.controller.PageController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 20:52
 * @Description: todo
 **/
@Controller
@RequestMapping("/")
@Slf4j
public class PageController {
    /**
     * 跳转到用户管理的主页面
     *
     * @return
     */
    @RequestMapping("/user_manager")
    public String userManager() {
        return "users/user_info";
    }

    /**
     * 跳转到角色管理的主页面
     *
     * @return
     */
    @RequestMapping("/role_manager")
    public String roleManager() {
        return "role/role_info";
    }

    /**
     * 跳转到权限管理的主页面
     *
     * @return
     */
    @RequestMapping("/permission_manager")
    public String permissionManager() {
        return "permission/permission_info";
    }

    /**
     * 跳转到sql页面
     *
     * @return
     */
    @RequestMapping("/sql_monitor")
    public String sqlMonitor() {
        return "system/sql_monitor";
    }

    /**
     * 跳转到sql页面
     *
     * @return
     */
    @RequestMapping("/log_monitor")
    public String logMonitor() {
        return "system/log_monitor";
    }
}

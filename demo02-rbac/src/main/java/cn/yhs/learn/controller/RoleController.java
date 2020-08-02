package cn.yhs.learn.controller;

import cn.yhs.learn.domain.ResultData;
import cn.yhs.learn.domain.Role;
import cn.yhs.learn.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.controller.RoleController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/24 10:08
 * @Description: todo
 **/
@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public RoleService roleService;

    /**
     * 分页查询所有角色以及该角色拥有的权限
     *
     * @param pageNum  当前页码数
     * @param pageSize 每页显示的数量
     * @return
     */
    @RequestMapping("/findAll")
    public ResultData findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roleList = roleService.findAll();
        PageInfo<Role> rolePageInfo = PageInfo.of(roleList);
        return ResultData.SUCCESS(rolePageInfo);
    }

    /**
     * 不分页进行查询所有角色数据
     *
     * @return
     */
    @RequestMapping(value = "/findAllNoPage", method = RequestMethod.GET)
    public ResultData findAllNoPage() {
        List<Role> roleList = roleService.findAll();
        return ResultData.SUCCESS(roleList);
    }


    /**
     * 添加角色
     *
     * @param role 角色实体类对象
     * @return
     */
    @RequestMapping(path = "/addRole", method = RequestMethod.POST)
    public ResultData addRole(Role role) {
        roleService.addRole(role);
        return ResultData.SUCCESS();
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @RequestMapping(path = "/updateRole", method = RequestMethod.POST)
    public ResultData updateRole(Role role) {
        roleService.updateRole(role);
        return ResultData.SUCCESS();
    }

    /**
     * 根据角色id删除角色数据，删除的同时，要把该角色绑定的权限数据也删除
     * 涉及到2张表的删除
     *
     * @param id 角色id
     * @return
     */
    @RequestMapping(path = "/deleteRoleById", method = RequestMethod.POST)
    public ResultData deleteRoleById(Integer id) {
        roleService.deleteRoleById(id);
        return ResultData.SUCCESS();
    }

    /**
     * 批量删除角色
     *
     * @param ids 角色id数组
     * @return
     */
    @RequestMapping(path = "/deleteRoleByIds", method = RequestMethod.POST)
    public ResultData deleteRoleById(Integer[] ids) {
        roleService.deleteRoleByIds(ids);
        return ResultData.SUCCESS();
    }

    /**
     * 为指定角色删除指定的权限
     *
     * @param roleId        角色id
     * @param permissionIds 权限id数组
     * @return
     */
    @RequestMapping(value = "/deleteAllPermissionForRole", method = RequestMethod.POST)
    public ResultData deleteAllPermissionForRole(Integer roleId, Integer[] permissionIds) {
        log.info("删除权限==》传递的角色id:{},权限ids:{}", roleId, Arrays.toString(permissionIds));
        roleService.deleteAllPermissionForRole(roleId, permissionIds);
        return ResultData.SUCCESS();
    }

    /**
     * 批量为角色分配权限
     *
     * @param roleId        角色id
     * @param permissionIds 权限id数组
     * @return
     */
    @RequestMapping(value = "/assignAllPermissionForRole", method = RequestMethod.POST)
    public ResultData assignAllPermissionForRole(Integer roleId, Integer[] permissionIds) {
        log.info("分配权限==》传递的角色id:{},权限ids:{}", roleId, Arrays.toString(permissionIds));
        roleService.assignAllPermissionForRole(roleId, permissionIds);
        return ResultData.SUCCESS();
    }

}

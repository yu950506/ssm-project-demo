package cn.yhs.learn.controller;

import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.domain.ResultData;
import cn.yhs.learn.service.PermissionService;
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
 * @Name: cn.yhs.learn.controller.PermissionController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/24 10:17
 * @Description: todo
 **/
@RestController
@RequestMapping("/permission")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限数据不分页
     *
     * @return
     */
    @RequestMapping("/findAllNoPage")
    public ResultData findAllNoPage() {
        List<Permission> permissionList = permissionService.findAll();
        return ResultData.SUCCESS(permissionList);
    }

    /**
     * 分页查询所有权限
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll")
    public ResultData findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Permission> permissionList = permissionService.findAll();
        PageInfo<Permission> permissionPageInfo = PageInfo.of(permissionList);
        return ResultData.SUCCESS(permissionPageInfo);
    }

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    public ResultData addPermission(Permission permission) {
        log.info("传递过来的permission数据：{}", permission);
        permissionService.addPermission(permission);
        return ResultData.SUCCESS(); // 添加成功
    }

    /**
     * 根据权限id删除权限数据
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/deletePermissionById", method = RequestMethod.POST)
    public ResultData deleteByPermissionId(Integer id) {
        log.info("传递过来的权限id:{}", id);
        permissionService.deletePermissionById(id);
        return ResultData.SUCCESS();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(path = "/deletePermissionByIds", method = RequestMethod.POST)
    public ResultData deleteByPermissionIds(Integer[] ids) {
        log.info("传递过来的权限ids:{}", Arrays.toString(ids));
        permissionService.deletePermissionByIds(ids);
        return ResultData.SUCCESS();
    }

    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    public ResultData updatePermission(Permission permission) {
        permissionService.updatePermission(permission);
        return ResultData.SUCCESS();
    }
}

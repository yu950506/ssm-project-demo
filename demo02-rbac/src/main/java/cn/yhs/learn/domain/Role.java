package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.domain.Role
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 15:22
 * @Description: 角色表
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    private Integer id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissionList; //一个角色有多个权限
}

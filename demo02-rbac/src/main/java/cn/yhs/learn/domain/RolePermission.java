package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.domain.RolePermission
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 15:28
 * @Description: 角色权限中间表
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
}

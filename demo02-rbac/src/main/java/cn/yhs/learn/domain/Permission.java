package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.domain.Permission
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 15:25
 * @Description: 权限表
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Permission {
    private Integer id;
    private String permissionName;
    private String permissionUrl;
    private String permissionDesc;
}

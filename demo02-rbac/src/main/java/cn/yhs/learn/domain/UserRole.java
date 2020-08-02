package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.domain.UserRole
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/19 15:24
 * @Description: 用户角色中间表
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRole {
    private Integer id;
    private Integer userId;
    private Integer roleId;
}

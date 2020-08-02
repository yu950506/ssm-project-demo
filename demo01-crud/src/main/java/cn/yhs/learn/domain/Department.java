package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.domain.Department
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/4 14:44
 * @Description: 部门实体类
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {
    private Integer id;
    private String name;
    private String description;
}

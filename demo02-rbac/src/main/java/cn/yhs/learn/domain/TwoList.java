package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.domain.TwoList
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/18 10:40
 * @Description: 二级列表
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TwoList {
    private Integer id;
    private String name;
    private String icon;
    private String url;
}

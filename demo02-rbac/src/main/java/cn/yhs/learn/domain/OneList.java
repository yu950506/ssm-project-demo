package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.domain.OneList
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/18 10:27
 * @Description: 一级菜单列表
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OneList {
    private Integer id;
    private String name;
    private String icon;
    private String url;
    private List<TwoList> twoLists; // 1:N的关系，一个1级列表对应多个二级列表
}

package cn.yhs.learn.domain;

import cn.yhs.learn.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.domain.SysLog
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/23 13:50
 * @Description: 操作日志
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysLog {
    private Integer id;
    private String username; // 执行的用户
    private String ip; // ip
    private String url; // 执行的url
    private String requestType; // 请求类型
    //  private String requestParam; // 请求参数
    private String clazz; // 执行的当前类
    private String method; // 执行的方法
    private Date visitTime; // 访问的开始时间
    private String visitTimeStr;
    private int executionTime; // 执行时长

    public String getVisitTimeStr() {
        return this.getVisitTime() != null ? DateUtils.date2String(this.getVisitTime()) : "0000-00-00 00:00:00";
    }
}

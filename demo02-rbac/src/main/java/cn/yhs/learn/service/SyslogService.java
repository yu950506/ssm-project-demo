package cn.yhs.learn.service;

import cn.yhs.learn.domain.SysLog;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.service.SyslogService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/23 15:15
 * @Description: todo
 **/
public interface SyslogService {
    List<SysLog> findAll();

    void saveSysLog(SysLog sysLog);
}

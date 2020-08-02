package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.SysLogDao;
import cn.yhs.learn.domain.SysLog;
import cn.yhs.learn.service.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.service.impl.SyslogServiveImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/23 15:16
 * @Description: todo
 **/
@Service
public class SyslogServiceImpl implements SyslogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    @Override
    public void saveSysLog(SysLog sysLog) {
        sysLogDao.saveSysLog(sysLog);
    }
}

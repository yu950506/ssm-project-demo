package cn.yhs.learn.controller;

import cn.yhs.learn.domain.ResultData;
import cn.yhs.learn.domain.SysLog;
import cn.yhs.learn.service.SyslogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.controller.SysLogControllerLog
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/23 15:14
 * @Description: todo
 **/
@Controller
@RequestMapping("/syslog")
public class SyslogController {
    @Autowired
    private SyslogService syslogService;

    @RequestMapping("/findAll")
    @ResponseBody
    public ResultData findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        // 使用分页插件进行分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<SysLog> sysLogList = syslogService.findAll();
        PageInfo<SysLog> logPageInfo = PageInfo.of(sysLogList);
        return ResultData.SUCCESS(logPageInfo.getTotal(), "成功", logPageInfo);
    }


}

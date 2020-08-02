package cn.yhs.learn.controller;

import cn.yhs.learn.domain.OneList;
import cn.yhs.learn.domain.ResultData;
import cn.yhs.learn.service.OneListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.controller.OneListController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/18 14:42
 * @Description: todo
 **/
@Controller
@RequestMapping("/oneList")
public class OneListController {
    @Autowired
    private OneListService oneListService;
    @ResponseBody
    @RequestMapping("/findAll")
    public ResultData findAllOneWithTwoList() {
        List<OneList> oneLists = oneListService.findAll();
        if (oneLists != null && oneLists.size() > 0)
            return ResultData.SUCCESS(oneLists);
        else
            return ResultData.FAIL();
    }


}

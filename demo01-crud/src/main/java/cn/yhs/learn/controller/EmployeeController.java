package cn.yhs.learn.controller;

import cn.yhs.learn.domain.Employee;
import cn.yhs.learn.domain.ResultData;
import cn.yhs.learn.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.controller.EmployeeController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/5 7:59
 * @Description: todo
 **/
@Controller
@RequestMapping("/emp")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页查询所有员工
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAll")
    public ResultData findAllEmp(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        // 1. 使用分页插件进行分页切入
        PageHelper.startPage(pageNum, pageSize);
        // 2. 调用service层的方法
        List<Employee> employeeList = employeeService.findAll();
        // 3. 把查询的数据封装成分页对象
        PageInfo<Employee> employeePageInfo = PageInfo.of(employeeList);
        return ResultData.SUCCESS(employeePageInfo);
    }

    /**
     * 前端校验玩之后由进行后端校验，使用JSR303校验
     *
     * @param employee
     * @param result
     * @return
     */
    @PostMapping("/saveEmp")
    @ResponseBody
    public ResultData saveEmp(@Validated Employee employee, BindingResult result) {
        log.info("传递过来的数据：{}", employee);
        Map<String, String> errInfo = new HashMap<>();
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                String field = error.getField();
                String message = error.getDefaultMessage();
                errInfo.put(field, message);
            }
            return ResultData.FAIL(errInfo);
        }
        employeeService.save(employee);
        return ResultData.SUCCESS();
    }

    @ResponseBody
    @GetMapping("/findByEmpId")
    public ResultData findByEmpId(Integer empId) {
        log.info("请求的参数empId:{}", empId);
        Employee employee = employeeService.findByEmpId(empId);
        return ResultData.SUCCESS(employee);
    }

    /**
     * 前后端数据都进行校验
     *
     * @param employee
     * @return
     */
    @ResponseBody
    @PostMapping("/updateEmp")
    public ResultData updateEmp(@Validated Employee employee, BindingResult result) {
        log.info("员工更新传递的参数：{}", employee);
        // todo 后续将返回的结果集封装成一个工具类
        Map<String, String> errInfo = new HashMap<>();
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                String field = error.getField();
                String message = error.getDefaultMessage();
                errInfo.put(field, message);
            }
            return ResultData.FAIL(errInfo);
        }
        employeeService.updateEmp(employee);
        return ResultData.SUCCESS();
    }

    @ResponseBody
    @PostMapping("/deleteEmpById")
    public ResultData deleteEmpById(Integer empId) {
        employeeService.deleteEmpById(empId);
        return ResultData.SUCCESS();
    }

    @ResponseBody
    @PostMapping("/deleteAllIds")
    public ResultData deleteAllIds(Integer[] ids) {
        log.info("传递过来的参数：{}", Arrays.toString(ids));
        employeeService.deleteAllIds(ids);
        return ResultData.SUCCESS();
    }


}

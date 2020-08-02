package cn.yhs.learn.aspect;

import cn.yhs.learn.domain.SysLog;
import cn.yhs.learn.service.SyslogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.aspect.ControllerLog
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/23 13:41
 * @Description: Controller层操作日志切面
 **/
// 声明成一个切面
@Aspect  // 开启切面支持
@EnableAspectJAutoProxy // 让Spring管理
@Component
@Slf4j
public class ControllerLogAspect {
    // 自动注入request对象，是为了获取用户的地址及相关信息
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SyslogService syslogService;
    private SysLog sysLog = new SysLog();
    private String username; // 执行的用户
    private String ip; // ip
    private String url; // 执行的url
    private String requestType; // 请求类型
    // private String requestParam; // 请求参数,获取有问题，后面待改进
    private String clazz; // 执行的当前类
    private String method; // 执行的方法
    private Date visitTime; // 访问的开始时间
    private int executionTime; // 执行时长

    /**
     * 配置切入点，只切面到Controller层
     */
    @Pointcut(value = "execution(* cn.yhs.learn.controller.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void logStart(JoinPoint jp) throws NoSuchMethodException {
        log.debug("start =============>controller层切面开始：记录访问日志");
        // 获取各个日志对象的参数，进行封装
        // todo 获取用户名,通多request对象获取当前登录的用户名
        username = "yuhansheng";
        sysLog.setUsername(username);
        // 获取ip,通过request对象进行获取
        ip = request.getRemoteHost();
        sysLog.setIp(ip);
        // 获取当前切入对象的类 clazz
        Class<?> objectClass = jp.getTarget().getClass();
        // 获取类的全名称
        clazz = objectClass.getName();
        sysLog.setClazz(clazz);
        // 获取当前切入点的 method
        method = jp.getSignature().getName();
        sysLog.setMethod(method);
        // 获取开始时间
        visitTime = new Date();
        sysLog.setVisitTime(visitTime);
        // 获取url：类上注解+方法上的注解@RequestMapping(value="",)
        // 获取类上的注解
        RequestMapping classRequestMapping = objectClass.getDeclaredAnnotation(RequestMapping.class);
        String classUrl = getValueOrPath(classRequestMapping);
        // 获取方法上的注解
        Object[] args = jp.getArgs();
        // 获取当前执行方法的所有参数
        // requestParam = Arrays.toString(args);
        // sysLog.setRequestParam(requestParam);
        Class[] argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = objectClass.getMethod(this.method, argsClass);
        // 获取当前执行方法上的注解
        RequestMapping methodRequestMapping = method.getDeclaredAnnotation(RequestMapping.class);
        String methodUrl = getValueOrPath(methodRequestMapping);
        url = classUrl + methodUrl;
        sysLog.setUrl(url);
        if (methodRequestMapping.method().length > 0) {
            RequestMethod requestMethod = methodRequestMapping.method()[0];
            // 默认的请求方式是GET
            requestType = requestMethod.name();
        } else {
            requestType = "GET";
        }
        sysLog.setRequestType(requestType);
    }

    @After(value = "pointCut()")
    public void logEnd() {
        // 获取执行时长
        executionTime = (int) (System.currentTimeMillis() - visitTime.getTime());
        sysLog.setExecutionTime(executionTime);
        // 保存日志,不要保存日志控制层的数据
        if (!"cn.yhs.learn.controller.SyslogController".equals(clazz))
            syslogService.saveSysLog(this.sysLog);
        log.debug("end ===========>controller层切面结束");
    }

    /**
     * 获取RequestMapping注解上的路劲值
     *
     * @param requestMapping
     * @return
     */
    public String getValueOrPath(RequestMapping requestMapping) {
        if (requestMapping.value().length > 0)
            return requestMapping.value()[0];
        if (requestMapping.path().length > 0)
            return requestMapping.path()[0];
        return "";
    }

}

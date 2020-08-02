package cn.yhs.learn.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.web.AppPathListener
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/18 15:36
 * @Description:
 **/
@Slf4j
public class AppPathListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String contextPath = servletContext.getContextPath();
        servletContext.setAttribute("AppPath", contextPath);
        log.info("设置整个系统路劲对象：AppPath = {}", contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

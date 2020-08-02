package cn.yhs.learn.dao;

import cn.yhs.learn.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.dao.SysLogDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/23 14:07
 * @Description: todo
 **/
@Repository
public interface SysLogDao {
    /**
     * 查询所有日志
     *
     * @return
     */
    @Select("select * from sys_log")
    @Results(id = "syslog", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "request_type", property = "requestType"),
            // @Result(column = "request_param", property = "requestParam"),
            @Result(column = "visit_time", property = "visitTime"),
            @Result(column = "execution_time", property = "executionTime")
    })
    List<SysLog> findAll();

    /**
     * 保存日志
     *
     * @param sysLog
     */
    @Insert("insert into sys_log(username,ip,url,clazz,method,visit_time,execution_time,request_type) value(#{username},#{ip},#{url},#{clazz},#{method},#{visitTime},#{executionTime},#{requestType})")
    void saveSysLog(SysLog sysLog);
}

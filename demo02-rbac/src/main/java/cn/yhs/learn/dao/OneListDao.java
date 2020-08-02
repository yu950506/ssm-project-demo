package cn.yhs.learn.dao;

import cn.yhs.learn.domain.OneList;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.dao.OneListDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/18 12:10
 * @Description: todo
 **/
@Repository
public interface OneListDao {
    /**
     * 查询所有一级列表并带有二级列表
     *
     * @return
     */
    @Select("select * from one_list")
    @Results(id = "oneWithTwo", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "icon", property = "icon"),
            @Result(column = "url", property = "url"),
            @Result(column = "id", property = "twoLists", many = @Many(select = "cn.yhs.learn.dao.TwoListDao.findTwoListByOneId"))
    })
    List<OneList> findAllWithTwoList();
}

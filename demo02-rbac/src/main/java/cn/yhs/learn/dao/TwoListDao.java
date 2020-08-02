package cn.yhs.learn.dao;

import cn.yhs.learn.domain.TwoList;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.dao.TwoList
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/18 12:16
 * @Description: 二级列表
 **/
@Repository
public interface TwoListDao {
    /**
     * 根据1级id查询所有的二级列表
     *
     * @param oneId 关联的1级id
     * @return
     */
    @Select("select * from two_list where one_id = #{oneId}")
    List<TwoList> findTwoListByOneId(Integer oneId);
}

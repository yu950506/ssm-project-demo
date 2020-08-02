package cn.yhs.learn.domain;

import cn.yhs.learn.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.domain.Users
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/18 9:36
 * @Description: 用户表
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer id;
    private String username;
    private String password;
    private Boolean gender; // 0:男 1：女
    private String genderStr;
    private String email;
    private Date createTime;
    private String createTimeStr;
    private Date updateTime;
    private String updateTimeStr;
    private Boolean status; // 0 :开启 1 ：关闭
    private String statusStr;
    private List<Role> roleList;

    public String getGenderStr() {
        return gender == true ? "男" : "女";
    }

    public String getStatusStr() {
        return status == true ? "开启" : "关闭";
    }

    public String getCreateTimeStr() {
        return this.getCreateTime() != null ? DateUtils.date2String(this.getCreateTime()) : "0000-00-00 00:00:00";
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime() != null ? DateUtils.date2String(this.getUpdateTime()) : "0000-00-00 00:00:00";
    }


}

package cn.yhs.learn.domain;

import cn.yhs.learn.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.domain.Employee
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/4 11:53
 * @Description: 数据库对应的实体类 Employee
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private Integer id;
    // JSR 303的注解校验，字段非空
    @NotEmpty
    private String name;
    private Character gender;
    // 邮箱正则表达式
    @Pattern(regexp = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", message = "邮箱格式不合法")
    private String email;
    // 日期必须是之前的日期
    @Past(message = "日期必须是之前的日期")
    private Date birthday;
    private String birthdayStr;
    // 部门id
    private Integer deptId;
    // 1个员工只能属于一个部门
    private Department department;

    public Employee(Integer id, String name, Character gender, String email, Date birthday, Integer deptId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.birthday = birthday;
        this.deptId = deptId;
    }

    public Employee(Integer id, String name, Character gender, String email, Date birthday, Integer deptId, Department department) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.birthday = birthday;
        this.deptId = deptId;
        this.department = department;
    }

    public String getBirthdayStr() {
        return DateUtils.date2String(this.birthday);
    }
}

package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.domain.ResultData
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/6 12:10
 * @Description: 通用的返回数据类型
 * {
 * "code": 0, 返回错误码 0表示成功
 * "count": 0, 返回总条数
 * "data": {}, 返回对象/数据列表
 * "msg": "成功" 返回详细描述
 * }
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultData {
    // 响应的业务状态码
    private Integer code;
    // 响应的业务数据总条数
    private Long count;
    // 响应消息
    private String msg;
    // 响应的数据
    private Object data;


    // 成功
    public static ResultData SUCCESS(Integer code, Long count, String msg, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setCount(count);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    public static ResultData SUCCESS(Long count, String msg, Object data) {
        return FAIL(200, count, msg, data);
    }

    public static ResultData SUCCESS(String msg, Object data) {
        return FAIL(200, 1L, msg, data);
    }

    public static ResultData SUCCESS(Object data) {
        return FAIL(200, 1L, "成功", data);
    }

    public static ResultData SUCCESS() {
        return FAIL(200, 1L, "成功", null);
    }


    // 失败
    public static ResultData FAIL(Integer code, Long count, String msg, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setCount(count);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    public static ResultData FAIL(Long count, String msg, Object data) {
        return FAIL(100, count, msg, data);
    }

    public static ResultData FAIL(String msg, Object data) {
        return FAIL(100, 0L, msg, data);
    }

    public static ResultData FAIL(Object data) {
        return FAIL(100, 0L, "失败", data);
    }

    // 100 是失败的状态
    public static ResultData FAIL() {
        return FAIL(100, 0L, "失败", null);
    }
}
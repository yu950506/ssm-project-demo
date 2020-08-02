package cn.yhs.learn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: ssm-project
 * @Name: cn.yhs.learn.utils.DateUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/6 12:34
 * @Description: todo
 **/
public class DateUtils {
    /**
     * 将日期转换成字符串，默认yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, "yyyy/MM/dd");
    }

    public static String date2String(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

}

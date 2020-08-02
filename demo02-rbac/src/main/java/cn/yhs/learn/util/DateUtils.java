package cn.yhs.learn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.util.DateUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/20 15:02
 * @Description: todo
 **/
public class DateUtils {
    public static String date2String(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String date2String(Date date) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return date2String(date, format);
    }

    public static void main(String[] args) {
        Date date = new Date();
        String s = date2String(date);
        System.out.println("s = " + s);
    }

}

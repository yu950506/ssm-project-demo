package cn.yhs.learn.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ProjectName: ssm-project-demo
 * @Name: cn.yhs.learn.util.PasswordUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/7/29 14:54
 * @Description: Shiro加密的方式
 **/
public class PasswordUtils {
    public static String shiroMd5(String password) {
        // 散列次数两次，自定义的加盐规则
        return new SimpleHash("md5", password, ByteSource.Util.bytes("喻汉生加的盐"), 2).toString();
    }

    public static void main(String[] args) {
        String s = shiroMd5("123456");
        System.out.println("s = " + s);
    }

}

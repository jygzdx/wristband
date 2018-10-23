package com.slogan.wristband.wristband.utils;

import android.util.Base64;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class StringUtils {
    /**
     * 判断去除空格后是否为空
     *
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isBlank(String str)
    {
        return (str == null || str.trim().length() == 0 || str.equals("")|| str.equals("null"));
    }
    public static boolean isNumeric(String str)
    {
        if (str == null || str.equals(""))
        {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 获判断是否为手机号
     *
     * @param telNum
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isPhoneNum(String telNum)
    {
        String regex = "^[1][0-9]{10}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
    }

    public static String urlFormat(String url)
    {
        return url.replace("\\", "");
    }
    /**
     * 获取UUID
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getRanUUID()
    {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    public static boolean isNoEmpty(String s){
        return s!=null&&s.length()!=0;
    }
}

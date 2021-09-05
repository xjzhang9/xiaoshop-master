package com.xjzhang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  时间工具类
 * @author xjzhang
 * @version 1.0
 * @date 2021/9/5 10:34
 */
public class DateUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String format (Date date) {
        return format(date, DATE_TIME_FORMAT);
    }

    public static String format(String date, String patern) {
        SimpleDateFormat df = new SimpleDateFormat(patern);
        return df.format(date);
    }

    public static String format(Date date, String patern) {
        SimpleDateFormat df = new SimpleDateFormat(patern);
        return df.format(date);
    }
}

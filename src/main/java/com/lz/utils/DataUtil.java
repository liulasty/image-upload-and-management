package com.lz.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/14/11:52
 * @Description:
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据效用
 *
 * @author lz
 * @date 2023/11/14
 */
public class DataUtil {
    /**
     * 字符串到数据
     *
     * @param s s
     *
     * @return {@code Date}
     */
    public static Date StringToData(String s){

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 日期到字符串
     *
     * @param date 日期
     *
     * @return {@code String}
     */
    public static String dateToString(Date date){

        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}
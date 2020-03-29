package com.zhxx.service.szxt.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @program: website
 * @author: 王新春
 * @create: 2018-09-08 15:24
 * @description: 时间转换
 **/

public class DateUtil {

    /**
     * 将日期转换成GMT日期 方法
     *
     * @param date
     * @return
     */
    public static Date getTimeToGMT(Date date) {
//        Date date = DateUtil.parseWithEnLag(jsonObject.getString("fDate"), "yyyy-MM-dd HH:mm:ss");//这里得到需要转换的北京时间
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, -8);//得到yyyy-MM-dd HH:mm:ss格式的世界时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String airtime = sdf.format(ca.getTime());
//        ca.set(year, month - 1, day);//设置日历时间，月份必须减一
        Date date1 = ca.getTime(); // 从一个 Calendar 对象中获取 Date 对象
//        airtime = airtime+"(Z)";//得到需要格式的世界时间
        return date1;
    }
}

package com.jiurong.hcxboot.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description
 */
public class TimeUtils {
    public static String getTime(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(time);
    }

    public static String getTimeYMD(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(time);
    }

    public static String getTimeYM(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        return sf.format(time);
    }

    public static String getTimeY(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.format(time);
    }

    @SneakyThrows
    public static Date getTimeDateYMD(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.parse(sf.format(time));
    }

    @SneakyThrows
    public static Date getTimeDateYM(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        return sf.parse(sf.format(time));
    }

    @SneakyThrows
    public static Date getTimeDateY(long time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.parse(sf.format(time));
    }


    @SneakyThrows
    public static Date getTimeByYMD(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.parse(time);
    }

    @SneakyThrows
    public static Date getTimeByYM(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        return sf.parse(time);
    }

    @SneakyThrows
    public static Date getTimeByY(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.parse(time);
    }


    public static long getDayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    public static long getMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    public static long getNextDayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime().getTime();
    }

    public static long getNextMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime().getTime();
    }
}

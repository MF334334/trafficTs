package com.example.trafficts.Time_Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {

    public static long[] getHistroyTimeOfSixHours() {
//        long nowTime = System.currentTimeMillis();
        long nowTime = 1538566733378L;
        long[] SixHourHistory = {0,0,0,0,0,0};
        for (int i = 5; i >= 0; i--) {
            SixHourHistory[i] = nowTime;
            nowTime = nowTime -3600000;
        }
        //下标越小 时间越靠前
        return SixHourHistory;
    }

    /*获取星期几*/
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /*获取星期几*/
    public static String getNumWeek(int i) {

        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    public static String getToday() {
        SimpleDateFormat simpleDateFormat;// HH:mm:ss
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);

    }


    public static int stringToWeek(String dt) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dt);
        cal.setTime(new Date(date.getTime()));

        return cal.get(Calendar.DAY_OF_WEEK);
    }

}

package com.pearadmin.pro.common.tools.core.lang;

public class DateUtil {

    public static String secondToTime(long second){
        long days = second /86400;
        second = second %86400;
        long hours = second /3600;
        second = second %3600;
        long minutes = second /60;
        second = second %60;
        if (0 < days) return String.format("%s天%s小时%s分%s秒",days,hours,minutes,second);
        else if(0 < hours) return String.format("%s小时%s分%s秒",hours,minutes,second);
        else if(0 < minutes) return String.format("%s分%s秒",minutes,second);
        else return String.format("%s秒",second);
    }
}

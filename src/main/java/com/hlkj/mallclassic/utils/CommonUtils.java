package com.hlkj.mallclassic.utils;

import java.util.Date;

/**
 * @program: mall-classic
 * @description:
 * @author: 李向平
 * @create: 2021-03-17 15:31
 */
public class CommonUtils {

    public static Boolean isInTimeLine(Date date, Date start, Date end){
        long time = date.getTime();
        long startTime = start.getTime();
        long endTime = end.getTime();
        if (time>startTime && time<endTime){
            return true;
        }
        return false;
    }

}

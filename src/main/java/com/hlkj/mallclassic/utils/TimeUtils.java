package com.hlkj.mallclassic.utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeUtils {

    /**
     * 获取当前时间戳
     * @return
     */
    public static Timestamp getCurrTimeStamp(){
        long time = Calendar.getInstance().getTime().getTime();
        return new Timestamp(time);
    }
}

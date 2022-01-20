package com.hlkj.mallclassic.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @program: mall-classic
 * @description: 订单工具类
 * @author: 李向平
 * @create: 2021-03-30 14:53
 */

@Component
public class OrderUtils {

    private static String[] yearCodes;

    @Value(value = "${qfk.year-codes}")
    public void setYearCodes(String yearCodes) {
        String[] split = yearCodes.split(",");
        OrderUtils.yearCodes = split;
    }

    /**
     * 生成订单号：年份字母+月日+毫秒后5位+微秒后3位+2位随机数
     * @return
     */
    public static String generateOrderNo(){
        StringBuffer joiner = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        String mills = String.valueOf(calendar.getTimeInMillis());//毫秒
        String micro = LocalDateTime.now().toString();//微秒
        String random = String.valueOf(Math.random() * 1000).substring(0, 2);//两位随机数
//        String[] yearCodes = OrderUtils.yearCodes;

        joiner.append(yearCodes[calendar.get(Calendar.YEAR)-2021])
                .append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(mills.substring(mills.length()-5))
                .append(micro.substring(micro.length()-3))
                .append(random);
        return joiner.toString();
    }

}

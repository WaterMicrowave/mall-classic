package com.hlkj.designmode.decorator;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-04-06 18:33
 */
public class CalendarTest {

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        instance.getActualMaximum(Calendar.SECOND);
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));
        System.out.println(instance.getTimeInMillis());
        System.out.println(instance.getMaximum(Calendar.YEAR));
//        Class.forName()

        System.out.println("================");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getYear());
    }
}

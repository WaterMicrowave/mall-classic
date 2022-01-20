package com.hlkj.interview;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-04-06 14:48
 */
public class Singleton {

    private static Singleton singleton;
    private Singleton() {
    }

    public static Singleton getInstance(){
        if(null == singleton){
            synchronized (Singleton.class) {
                if(null == singleton){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

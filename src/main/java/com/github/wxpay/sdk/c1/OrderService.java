package com.github.wxpay.sdk.c1;

public abstract class OrderService {

    //优惠券核算
    abstract void couponHandle();
    //积分核算
    abstract void jiFenHandle();

    /**
     * 生成订单
     */
    void orderPlace(){
        System.out.println("下单前的校验");
        //1、优惠券
        couponHandle();
        //2、积分
        jiFenHandle();
        //3、其他逻辑
        System.out.println("其他下单逻辑代码");
        System.out.println("其他下单逻辑代码");
        System.out.println("其他下单逻辑代码");
        System.out.println("下单完成");
    }

}

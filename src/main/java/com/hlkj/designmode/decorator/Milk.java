package com.hlkj.designmode.decorator;

/**
 * @program: mall-classic
 * @description: 具体装饰类
 * @author:
 * @create: 2021-04-08 21:21
 */
public class Milk extends Decorator {
    @Override
    public void showCoffee() {
        //装饰模式在转发请求之前增加附加功能
        System.out.print("加奶");
        super.showCoffee();
        //装饰模式在转发请求之后增加附加功能
    }

    @Override
    public double showPrice() {
        return 15.0+super.showPrice();
    }
}

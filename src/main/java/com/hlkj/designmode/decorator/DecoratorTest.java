package com.hlkj.designmode.decorator;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-04-08 21:23
 */
public class DecoratorTest {
    public static void main(String[] args) throws Exception {
        Coffee coffee = new Coffee("拿铁", 55.0);
        Decorator sugar = new Sugar();
        Decorator milk = new Milk();
        //装饰模式扩展的是对象的功能
        sugar.setIComponent(coffee);//已存在类的动态组合
        milk.setIComponent(sugar);//已存在类的动态组合
        milk.showCoffee();
        System.out.println(milk.showPrice());

        DataInputStream stream = new DataInputStream(new BufferedInputStream(new FileInputStream("")));
        stream.read(null);
    }
}

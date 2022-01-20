package com.hlkj.designmode.decorator2;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-04-08 22:30
 */
public abstract class CakeDecorator extends Cake {
    private Cake cake;

    public CakeDecorator(Cake cake) {
        this.cake = cake;
    }

    public abstract String getName();
}

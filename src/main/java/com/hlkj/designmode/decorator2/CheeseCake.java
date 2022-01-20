package com.hlkj.designmode.decorator2;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-04-08 22:28
 */
public class CheeseCake extends Cake {

    public CheeseCake() {
        super.name = "乳酪蛋糕";
    }

    @Override
    String getFeel() {
        return "香甜味道";
    }
}

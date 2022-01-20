package com.hlkj.designmode.decorator;

/**
 * @program: mall-classic
 * @description: 抽象装饰类
 * @author:
 * @create: 2021-04-08 21:17
 */
public abstract class Decorator implements IComponent {
    //真实对象的索引
    private IComponent iComponent;

    public void setIComponent(IComponent iComponent) {
        this.iComponent = iComponent;
    }

    @Override
    public void showCoffee() {
        //真实对象调用
        iComponent.showCoffee();
    }

    @Override
    public double showPrice() {
        //真实对象调用
        return iComponent.showPrice();
    }
}

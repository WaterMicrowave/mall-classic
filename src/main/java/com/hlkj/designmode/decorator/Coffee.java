package com.hlkj.designmode.decorator;

/**
 * @program: mall-classic
 * @description: 被装饰类
 * @author:
 * @create: 2021-04-08 21:13
 */
public class Coffee implements IComponent{
    private String name;
    private double price;

    public Coffee(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void showCoffee() {
        System.out.println(this.getName()+"咖啡");
    }

    @Override
    public double showPrice() {
        return this.getPrice();
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

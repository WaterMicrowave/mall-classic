package com.hlkj.designmode.decorator2;

public abstract class Cake {
    String name = "蛋糕";

    public String getName() {
        return name;
    }

    abstract String getFeel();
}

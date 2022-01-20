package com.hlkj.mallclassic.core.money;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @program: mall-classic
 * @description: 向上取整
 * @author: 李向平
 * @create: 2021-03-21 14:48
 */
@Component
@Primary
public class HalfUpRound implements IMoneyDiscount {

    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discount) {
        BigDecimal actualMoney = original.multiply(new BigDecimal("1").subtract(discount));
//        BigDecimal decimal = original.subtract(actualMoney);
        BigDecimal finalMoney = actualMoney.setScale(2, RoundingMode.HALF_UP);
        return finalMoney;
    }
}

package com.hlkj.mallclassic.core.money;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @program: mall-classic
 * @description:银行家模式
 * @author: 李向平
 * @create: 2021-03-21 14:55
 */
@Component
public class HalfEvenRound implements IMoneyDiscount {
    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discount) {
        BigDecimal actualMoney = original.multiply(discount);
        BigDecimal finalMoney = actualMoney.setScale(2, RoundingMode.HALF_EVEN);
        return finalMoney;
    }
}

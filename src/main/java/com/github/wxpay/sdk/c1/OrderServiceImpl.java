package com.github.wxpay.sdk.c1;


import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends OrderService {
    @Override
    void couponHandle() {
        System.out.println("优惠券核算正确");
    }

    @Override
    void jiFenHandle() {
        System.out.println("积分核算正确");
    }
}

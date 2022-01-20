package com.github.wxpay.sdk.c1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderContoller {

    @Resource
    private OrderService orderService;

    @PostMapping("/place/order")
    public Integer orderPlace(){
        orderService.orderPlace();

        return 1;
    }

}

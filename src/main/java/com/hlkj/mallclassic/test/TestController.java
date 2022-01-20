package com.hlkj.mallclassic.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @program: mall-classic
*
* @description: 测试
*
* @author:
*
* @create: 2021-03-21 15:30
*/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private Counter counter;

    @GetMapping("/lxp")
    public void getCount(){
        System.out.println(this.counter);
    }

}

package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.common.UnifyResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall-classic
 * @description: redis测试
 * @author:
 * @create: 2021-04-02 19:30
 */
@RestController
public class JedisTestController {

    @RequestMapping("/spu/redis")
    public UnifyResponse test() {

        return null;
    }

}

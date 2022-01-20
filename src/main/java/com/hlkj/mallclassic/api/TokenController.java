package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall-classic
 * @description:
 * @author: lxp
 * @create: 2021-03-27 12:25
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/verify")
    public UnifyResponse  tokenVerify(@RequestParam String token){
        Boolean verify = tokenService.tokenVerify(token);
        return UnifyResponse.buildSuccess(verify);
    }

}

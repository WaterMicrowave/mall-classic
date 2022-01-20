package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.annotations.ScopeLevel;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.utils.JwtTokenUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api
 * @ClassName: TokenController
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 17:23
 * @Version: 1.0
 */
@RestController
@RequestMapping("/token")
public class TokenTestController {

    @RequestMapping("/login")
    public UnifyResponse login(@RequestParam String userId){
        JwtTokenUtils tokenUtils = new JwtTokenUtils();
        String token = JwtTokenUtils.makeToken(userId);

        return UnifyResponse.buildSuccess(token);
    }

    @RequestMapping("/user/info")
    @ScopeLevel(2)
    public UnifyResponse userInfo(HttpServletRequest request){

        return UnifyResponse.buildSuccess("token测试成功");
    }

}

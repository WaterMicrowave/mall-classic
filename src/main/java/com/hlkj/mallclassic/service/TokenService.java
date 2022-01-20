package com.hlkj.mallclassic.service;

import com.auth0.jwt.interfaces.Claim;
import com.hlkj.mallclassic.annotations.ScopeLevel;
import com.hlkj.mallclassic.core.LocalUser;
import com.hlkj.mallclassic.exception.TokenAuthFailedException;
import com.hlkj.mallclassic.model.User;
import com.hlkj.mallclassic.utils.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @program: mall-classic
 * @description:
 * @author: 李向平
 * @create: 2021-03-27 12:16
 */
@Service
public class TokenService {

    @Autowired
    private UserService userService;

    public Boolean tokenVerify(String token){
        if (StringUtils.isEmpty(token)){
            throw new TokenAuthFailedException();
        }
        //2、Token 验证(获取claims).如果过期过检验失败就会抛出异常
        Map<String, Claim> claims = JwtTokenUtils.getClaims(token);
        //3、将当前用户信息存入ThreadLocal
        String uid = claims.get("uid").asString();
        Integer scope = claims.get("scope").asInt();

        User user = userService.getUserById(uid);
        LocalUser.set(user, scope);

        return true;
    }

}

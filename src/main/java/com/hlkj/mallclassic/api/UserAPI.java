package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.service.UserService;
import com.hlkj.mallclassic.dto.TokenGetDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api
 * @ClassName: UserAPI
 * @Author: Administrator
 * @Description: 用户相关api
 * @Date: 2021/3/10 9:40
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserAPI {

    @Autowired
    private UserService userService;

    /**
     * 用户名密码登陆(适用于CMS)
     * @param tokenGetVO
     * @param response
     * @return
     */
    @RequestMapping("/login/username/pwd")
    public UnifyResponse loginUsernamePwd(@RequestBody @Validated TokenGetDTO tokenGetVO, HttpServletResponse response){
        //判断参数是否完整
        if(StringUtils.isEmpty(tokenGetVO.getAccount()) || StringUtils.isEmpty(tokenGetVO.getPassword())){
            throw new APIParamException();
        }
        userService.loginUsernamePwd(tokenGetVO, response);
        return UnifyResponse.buildSuccess("登录成功");
    }

}

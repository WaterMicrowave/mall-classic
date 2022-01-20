package com.hlkj.mallclassic.service;

import com.alibaba.fastjson.JSONObject;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.exception.UsernameOrPwdException;
import com.hlkj.mallclassic.model.User;
import com.hlkj.mallclassic.repository.UserRepository;
import com.hlkj.mallclassic.utils.JwtTokenUtils;
import com.hlkj.mallclassic.utils.UUIDUtils;
import com.hlkj.mallclassic.dto.TokenGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Optional;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.service
 * @ClassName: UserService
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 9:42
 * @Version: 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveWechatUser(JSONObject jsonObject, JSONObject userInfoJSON){
        User user = null;
        Boolean exist = this.wechatUserExist(jsonObject.getString("openid"));
        String openid = jsonObject.getString("openid");
        if(exist){
            user = userRepository.findFirstByOpenIdEquals(openid).get();
        }
        if(!exist){
            //组装User对象
            User savingUser = new User();
            savingUser.setId(UUIDUtils.generateUUID());
            savingUser.setNickName(URLEncoder.encode(userInfoJSON.getString("nickName")));
            savingUser.setAvatar(userInfoJSON.getString("avatarUrl"));
            savingUser.setOpenId(jsonObject.getString("openid"));
            savingUser.setUnionId(jsonObject.getString("unionid"));
            System.out.println(jsonObject.getString("session_key"));

            user = userRepository.save(savingUser);
        }
        return user;
    }

    /**
     * 判断微信用户是否已存在
     * @param openid
     * @return
     */
    private Boolean wechatUserExist(String openid){
        boolean present = userRepository.findFirstByOpenIdEquals(openid).isPresent();
        return present;
    }

    /**
     * 用户名+密码 登录
     * @param tokenGetVO
     * @param response
     * @return
     */
    public void loginUsernamePwd(TokenGetDTO tokenGetVO, HttpServletResponse response){
        //1、从数据库查询
        Optional<User> optional = userRepository.findByNickNameAndPwd(tokenGetVO.getAccount(), tokenGetVO.getPassword());
        if(!optional.isPresent()){
            throw new UsernameOrPwdException();
        }
        //2、生成token
        String token = JwtTokenUtils.makeToken(optional.get().getId());
        //3、在response header返回
        response.setHeader(JwtTokenUtils.getHeader(), token);
    }

    public User getUserById(String id){
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        return user;
    }

}

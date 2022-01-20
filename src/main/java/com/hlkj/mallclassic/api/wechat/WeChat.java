package com.hlkj.mallclassic.api.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hlkj.mallclassic.annotations.ScopeLevel;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.configration.WxConfig;
import com.hlkj.mallclassic.service.WechatService;
import com.hlkj.mallclassic.dto.TokenGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class WeChat {

    @Autowired
    private WxConfig wxConfig;
    @Autowired
    private WechatService wechatService;

    /**
     * 获取openid
     * @param
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/openid")
    public UnifyResponse getSessionKeyOpenid(@RequestBody TokenGetDTO tokenGetVO) throws Exception {
        System.out.println("code:" + tokenGetVO.getAccount());
        System.out.println("iv:" + tokenGetVO.getIv());
        System.out.println("encryptedData:" + tokenGetVO.getEncryptedData());
//        wechatService.test(tokenGetVO);//也可以
        return wechatService.getOpenId(tokenGetVO);
    }

    /**
     * 预订单
     * @param orderId
     * @return 小程序 wx.requestPayment需要的数据
     * @throws Exception
     */
    @PostMapping("/pay/order/{id}")
//    @ScopeLevel
    public UnifyResponse<Map<String,String>> preWxOrder(@PathVariable(name = "id") String orderId) throws Exception {

        wechatService.preOrder(orderId);
        return null;
    }

    @RequestMapping("/notify")
    public String payCallback(HttpServletRequest request, HttpServletResponse response){

        return null;
    }

//    @RequestMapping("/unionid")
//    public String getUnionId(String encryptedData, String iv, String code)  {
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
//                + wxConfig.getAppid() + "&secret="
//                + wxConfig.getAppsercret() + "&js_code="
//                + code + "&grant_type=authorization_code";
//        try{
//            String str = WeChatUtil.httpRequest(url, "POST", null);
//            String session_key= JacksonUtil.getFileValue(str,"session_key").toString();
//            String result = AesUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
//            return result;
//        }
//        catch (Exception ex){
//            return null;
//        }
//    }
}
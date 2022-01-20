package com.hlkj.mallclassic.service;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wxpay.sdk.QfWXPayConfig;
import com.github.wxpay.sdk.WXPay;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.configration.WxConfig;
import com.hlkj.mallclassic.core.LocalUser;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.exception.OrderException;
import com.hlkj.mallclassic.model.Order;
import com.hlkj.mallclassic.model.User;
import com.hlkj.mallclassic.repository.OrderRepository;
import com.hlkj.mallclassic.utils.HttpRequestProxy;
import com.hlkj.mallclassic.utils.JwtTokenUtils;
import com.hlkj.mallclassic.utils.wechat.AecUtil;
import com.hlkj.mallclassic.dto.TokenGetDTO;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @program: mall-classic
 * @description: 微信相关接口
 * @author: 李向平
 * @create: 2021-03-20 16:41
 */
@Service
public class WechatService {

    @Autowired
    private WxConfig wxConfig;
    @Autowired
    private UserService userService;



    public String login(TokenGetDTO tokenGetVO){
        String code2Session = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String url = MessageFormat.format(code2Session, wxConfig.getAppid(), wxConfig.getAppsercret(),tokenGetVO.getAccount());
        RestTemplate restTemplate = new RestTemplate();
        Object sessionText = restTemplate.getForObject(url, String.class);
        cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(sessionText);
        return jsonObject.getStr("openid");
    }

    /**
     * 获取微信openid
     * @param tokenGetVO
     * @return
     * @throws JsonProcessingException
     */
    public UnifyResponse getOpenId(TokenGetDTO tokenGetVO) throws IOException, InterruptedException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //发送get请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        HttpGet httpget = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="
                + wxConfig.getAppid() + "&secret="
                + wxConfig.getAppsercret() + "&js_code=" + tokenGetVO.getAccount() + "&grant_type=authorization_code");
        CloseableHttpResponse response = httpclient.execute(httpget);

        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = JSON.parseObject(EntityUtils.toString((org.apache.http.HttpEntity) entity));

//-------------------------java.util.NoSuchElementException: No value present---------解密用户信息-----------------------------------------
        String userInfo = null;
        JSONObject userInfoJSON = null;
        try {
            byte[] resultByte = AecUtil.decrypt(Base64.decodeBase64(tokenGetVO.getEncryptedData()),
                    Base64.decodeBase64(jsonObject.getString("session_key")),
                    Base64.decodeBase64(tokenGetVO.getIv()));

            userInfo = new String(resultByte, "UTF-8");
            System.out.println("userInfo:" + userInfo);
            userInfoJSON = JSON.parseObject(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("userInfo:" + userInfoJSON);
//---------------------------------- 解密用户信息 -----------------------------------------

        //具体可以获取什么用户信息可以到微信小程序文档查看
        //注意：获取用户信息和获取用户手机号 这两个是单独获取的，不能同时获取
        System.out.println("openId:" + jsonObject.getString("openid"));
        System.out.println(userInfoJSON.getString("nickName"));
//----------------------------------调用用户service-----------------------------------------
        User user = userService.saveWechatUser(jsonObject,userInfoJSON);
        //生成token
        String token = JwtTokenUtils.makeToken(user.getId());
        return UnifyResponse.buildSuccess(token);
    }

    @Autowired
    private OrderRepository orderRepository;
    /**
     * 预订单的业务
     * @param orderId
     * @return
     */
    public Map<String,String> preOrder(String orderId) throws Exception {
//        String uid = LocalUser.getUser().getId();
//        //1、从数据库获取订单详情
//        Optional<Order> optional = orderRepository.findFirstByUserIdAndId(uid, orderId);
//        Order order = optional.orElseThrow(NotFoundException::new);
//        //2、判断订单是否需要取消
//        Boolean needCancel = order.needCancel();
//        if(needCancel){
//            throw new OrderException(800006);
//        }
        //3、拼接统一下单
        QfWXPayConfig config = new QfWXPayConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "测试商品");
        data.put("out_trade_no", "A202104026636");
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("openid", "oThf74kuZ3csD4CCNfJClxdhDknY");
        data.put("spbill_create_ip", HttpRequestProxy.getRemoteRealIp());
        data.put("total_fee", "1");
        data.put("trade_type", "JSAPI");  // 小程序支付

        Map<String, String> wxOrder = null;
        try {
            wxOrder = wxpay.unifiedOrder(data);
            System.out.println(wxOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!wxOrder.get("return_code").equals("SUCCESS")
                || !wxOrder.get("result_code").equals("SUCCESS")){
            throw new APIParamException();
        }

        return null;
    }

}

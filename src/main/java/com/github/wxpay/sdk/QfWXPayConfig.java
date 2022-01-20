package com.github.wxpay.sdk;


import java.io.InputStream;


public class QfWXPayConfig extends WXPayConfig{

    @Override
    String getAppID() {
        return "wxffe9f8ad93950a30";
    }

    @Override
    String getMchID() {
        return "1534636891 ";
    }

    @Override
    String getKey() {
        return "Hlkj1234Hlkj1234Hlkj1234Hlkj1234";
    }

    @Override
    InputStream getCertStream() {
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        // 这个方法需要这样实现, 否则无法正常初始化WXPay
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {

            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;

    }
}
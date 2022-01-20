package com.hlkj.mallclassic.configration;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @ProjectName: missyou
 * @Package: com.qfk.missyou.config
 * @ClassName: ErrorPageConfig
 * @Author: Administrator
 * @Description: 错误页面配置
 * @Date: 2020/11/9 11:04
 * @Version: 1.0
 */
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[1];
        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND,"/404.do");

        registry.addErrorPages(errorPages);
    }
}
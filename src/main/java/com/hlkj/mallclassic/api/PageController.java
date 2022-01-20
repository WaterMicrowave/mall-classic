package com.hlkj.mallclassic.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api
 * @ClassName: PageController
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 17:55
 * @Version: 1.0
 */
@Controller
public class PageController {

    @RequestMapping("/404.do")
    public String noPermission(){

        return "404";
    }

    @RequestMapping("/wel")
    public String index(){

        return "index";
    }
}

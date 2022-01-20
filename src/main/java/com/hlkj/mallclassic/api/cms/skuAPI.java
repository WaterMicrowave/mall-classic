package com.hlkj.mallclassic.api.cms;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.model.Sku;
import com.hlkj.mallclassic.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api.cms
 * @ClassName: skuAPI
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 11:51
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sku")
public class skuAPI {

    @Autowired
    private SkuService skuService;

    @RequestMapping("/add")
    public UnifyResponse add(@RequestBody Sku sku){
        return skuService.add(sku);
    }

    @RequestMapping("/{skuId}")
    public UnifyResponse add(@PathVariable String skuId){
        return skuService.detail(skuId);
    }

}

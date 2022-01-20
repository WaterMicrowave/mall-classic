package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.model.Banner;
import com.hlkj.mallclassic.service.BannerService;
import com.hlkj.mallclassic.vo.BannerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api
 * @ClassName: BannerAPI
 * @Author: Administrator
 * @Description: banner相关接口
 * @Date: 2021/3/9 14:45
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/banner")
public class BannerAPI {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/detail/{id}")
    public UnifyResponse getDetail(@PathVariable String id){
//        BannerVO bannerVO = bannerService.getDetail(id);
        Banner banner = bannerService.getDetail(id);
        return UnifyResponse.buildSuccess(banner);
    }

    @GetMapping(value = "/add")
    public UnifyResponse add(@RequestBody Banner banner){

        return  bannerService.add(banner);
    }

    @RequestMapping("/list/page/{pageNum}/{pageSize}")
    public UnifyResponse listByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize){

        return bannerService.listPageSort(pageNum, pageSize);
    }

    @RequestMapping("/list/vo/page/{pageNum}/{pageSize}")
    public UnifyResponse listVOByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) throws InvocationTargetException, IllegalAccessException {

        return bannerService.bannerVOListByPage(pageNum, pageSize);
    }

}

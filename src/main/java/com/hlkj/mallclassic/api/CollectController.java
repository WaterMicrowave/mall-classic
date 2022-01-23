package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.model.Collect;
import com.hlkj.mallclassic.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api
 * @ClassName: BannerAPI
 * @Author: Administrator
 * @Description: 收藏相关接口
 * @Date: 2022/1/23 04:45
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping(value = "/add")
    public UnifyResponse add(@RequestBody Collect collect){
        return  collectService.add(collect);
    }

    @GetMapping(value = "/remove")
    public UnifyResponse add(String spuId){
        return  collectService.remove(spuId);
    }

    @RequestMapping("/list/page/{pageNum}/{pageSize}")
    public UnifyResponse listByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize){

        return collectService.listPageSort(pageNum, pageSize);
    }

}

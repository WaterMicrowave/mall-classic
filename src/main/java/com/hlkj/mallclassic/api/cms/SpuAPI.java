package com.hlkj.mallclassic.api.cms;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.model.Spu;
import com.hlkj.mallclassic.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api.cms
 * @ClassName: SpuAPI
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 14:51
 * @Version: 1.0
 */
@RestController
@RequestMapping("/spu")
public class SpuAPI {

    @Autowired
    private SpuService spuService;

    @RequestMapping("/list/{pageNum}/{pageSize}/{sortColumn}")
    public UnifyResponse listByPageSort(@PathVariable Integer pageNum,
                                        @PathVariable Integer pageSize,
                                        @PathVariable String sortColumn){

        return spuService.listByPage(pageNum,pageSize,sortColumn);
    }

    @RequestMapping("/add")
    public UnifyResponse add(@RequestBody Spu spu){

        return null;
    }

}

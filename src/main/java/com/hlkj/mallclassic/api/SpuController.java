package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.service.SpuService;
import com.hlkj.mallclassic.vo.SpuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api
 * @ClassName: SpuController
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/15 16:43
 * @Version: 1.0
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    /**
     * 分页查询
     * @param start
     * @param count
     * @return
     */
    @RequestMapping("/list/page")
    public UnifyResponse listByPage(@RequestParam Integer start,
                                    @RequestParam Integer count){


        return spuService.listByPage(start, count, null);
    }

    @RequestMapping("/detail/{id}")
    public SpuVO getDetail(@PathVariable String id){

        return spuService.getDetail(id);
    }

}

package com.hlkj.mallclassic.service;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.model.Sku;
import com.hlkj.mallclassic.repository.SkuRepository;
import com.hlkj.mallclassic.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.service
 * @ClassName: SkuService
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 11:49
 * @Version: 1.0
 */
@Service
public class SkuService {

    @Autowired
    private SkuRepository skuRepository;

    public List<Sku> getList(List<String> ids){
        return skuRepository.findAllByIdIn(ids);
    }

    /**
     * 新增sku
     * @param sku
     * @return
     */
    public UnifyResponse add(Sku sku){
        sku.setId(UUIDUtils.generateUUID());
        Sku save = skuRepository.save(sku);

        return UnifyResponse.buildSuccess(save);
    }

    public UnifyResponse detail(String skuId){
        Sku sku = skuRepository.findById(skuId).orElseThrow(NotFoundException::new);

        return UnifyResponse.buildSuccess(sku);
    }


}

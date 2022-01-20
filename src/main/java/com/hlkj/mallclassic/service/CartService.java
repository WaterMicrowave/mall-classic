package com.hlkj.mallclassic.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.hlkj.mallclassic.model.Sku;
import com.hlkj.mallclassic.model.Spu;
import com.hlkj.mallclassic.repository.SkuRepository;
import com.hlkj.mallclassic.repository.SpuRepository;
import com.hlkj.mallclassic.vo.CartDataSyncVO;
import com.hlkj.mallclassic.vo.SkuVO;
import com.hlkj.mallclassic.vo.SpuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mall-classic
 * @description: 购物车
 * @author: 李向平
 * @create: 2021-03-24 13:06
 */
@Service
public class CartService {

    @Autowired
    private SpuRepository spuRepository;
    @Autowired
    private SkuRepository skuRepository;

    public List<CartDataSyncVO> syncCartData(String cartInfo){
        List<CartDataSyncVO> result = new ArrayList<>();

        String[] cartSpuOrSkuInfos = cartInfo.split("#");
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<Spu> spuList = null;
        List<Sku> skuList = null;
        List<SpuVO> spuVOList = new ArrayList<>();
        List<SkuVO> skuVOList = new ArrayList<>();
        for (String cartSpuOrSkuInfo:
                cartSpuOrSkuInfos) {
            String[] id_hasSku = cartSpuOrSkuInfo.split("-");//1-false
            if(Boolean.valueOf(id_hasSku[1])){
                if (null==skuList) {
                    skuList = new ArrayList<>();
                }
//                Sku sku = skuRepository.getOne(id_hasSku[0]);
                Sku sku = skuRepository.findById(id_hasSku[0]).get();
                skuList.add(sku);
            }
            else {
                if (null==spuList) {
                    spuList = new ArrayList<>();
                }
//                Spu spu = spuRepository.getOne(id_hasSku[0]);
                Spu spu = spuRepository.findById(id_hasSku[0]).get();
                spuList.add(spu);
            }
        }
        if(!ListUtils.isEmpty(spuList)){
            spuList.forEach(s->{
                SpuVO spuVO = mapper.map(s, SpuVO.class);
                //由于属性名不一致
                spuVO.setDiscount_price(s.getDiscountPrice());
                spuVOList.add(spuVO);
            });
        }
        if (!ListUtils.isEmpty(skuList)) {
            skuList.forEach(s->{
                SkuVO skuVO = mapper.map(s, SkuVO.class);
                //由于属性名不一致
                skuVO.setDiscount_price(s.getDiscountPrice());
                skuVOList.add(skuVO);
            });
        }

        if(!ListUtils.isEmpty(spuVOList)){
            spuVOList.forEach(spuVO -> {
                CartDataSyncVO cartDataSyncVO = new CartDataSyncVO();
                cartDataSyncVO.setId(spuVO.getId());
                cartDataSyncVO.setHasSku(false);
                cartDataSyncVO.setSpuVO(spuVO);
                result.add(cartDataSyncVO);
            });
        }
        if(!ListUtils.isEmpty(skuVOList)){
            skuVOList.forEach(skuVO -> {
                CartDataSyncVO cartDataSyncVO = new CartDataSyncVO();
                cartDataSyncVO.setId(skuVO.getId());
                cartDataSyncVO.setHasSku(true);
                cartDataSyncVO.setSkuVO(skuVO);
                result.add(cartDataSyncVO);
            });
        }
        return result;
    }

}

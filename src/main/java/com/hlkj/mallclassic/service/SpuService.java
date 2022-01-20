package com.hlkj.mallclassic.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.wenhao.jpa.Specifications;
import com.hlkj.mallclassic.common.PagingDozer;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.model.Sku;
import com.hlkj.mallclassic.model.Spu;
import com.hlkj.mallclassic.model.SpuDetailImg;
import com.hlkj.mallclassic.model.SpuImg;
import com.hlkj.mallclassic.repository.SkuRepository;
import com.hlkj.mallclassic.repository.SpuRepository;
import com.hlkj.mallclassic.vo.SkuVO;
import com.hlkj.mallclassic.vo.SpuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.service
 * @ClassName: SpuService
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:19
 * @Version: 1.0
 */
@Service
public class SpuService {

    @Autowired
    private SpuRepository spuRepository;
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private SpuImgService spuImgService;
    @Autowired
    private SpuDetailImgService spuDetailImgService;

    public List<Spu> getList(List<String> ids){
        return spuRepository.findAllByIdIn(ids);
    }

    /**
     * 获取spu详情
     * @param id
     * @return
     */
    public SpuVO getDetail(String id){
        Spu spu = spuRepository.findById(id).orElseThrow(NotFoundException::new);

        List<SpuImg> spuImgList = spuImgService.getById(id);
        List<SpuDetailImg> spuDetailImgList = spuDetailImgService.getById(id);
        List<Sku> list = skuRepository.findBySpuIdEquals(spu.getId());

        //考虑到小程序字，需转换为SkuVO
        ArrayList<SkuVO> skuVOList = new ArrayList<>();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        list.forEach(sku->{
            SkuVO skuVO = mapper.map(sku, SkuVO.class);
            skuVO.setDiscount_price(sku.getDiscountPrice());
            skuVO.setStock(sku.getStock());
            skuVO.setSpu_id(sku.getSpuId());
            skuVO.setCategoryId(sku.getCategoryId());
            skuVOList.add(skuVO);
        });

        SpuVO spuVO = new SpuVO();
        spuVO.setId(spu.getId());
        spuVO.setTitle(spu.getTitle());
        spuVO.setSubtitle(spu.getSubtitle());
        spuVO.setPrice(spu.getPrice());
        spuVO.setDiscount_price(spu.getDiscountPrice());
        spuVO.setCategoryId(spu.getCategoryId());
        spuVO.setImg(spu.getImg());
        spuVO.setTags(spu.getTags());
        spuVO.setDefault_sku_id("1");//需判断
        spuVO.setOnline(spu.getOnline());
        spuVO.setSku_list(skuVOList);
        spuVO.setSpu_img_list(spuImgList);
        spuVO.setSpu_detail_img_list(spuDetailImgList);
        spuVO.setStock(spu.getStock());
        return spuVO;
    }

    public UnifyResponse getSpuTop4(String cId){
        List<Spu> list = spuRepository.findByCategoryIdTop4(cId);

        return UnifyResponse.buildSuccess(list);
    }

    /**
     * 分页查询
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param sortColumn 排序字段
     * @return
     */
    public UnifyResponse listByPage(Integer pageNum, Integer pageSize, String sortColumn){
        PageRequest pageRequest = null;
        if(StringUtils.isEmpty(sortColumn)){
            pageRequest = PageRequest.of(pageNum, pageSize);
        }else{
            pageRequest = PageRequest.of(pageNum, pageSize, Sort.by(sortColumn).descending());
        }
        Spu spu = new Spu();
        spu.setRecommend(true);
        spu.setOnline(Byte.parseByte("1"));

        Specification<Spu> build = Specifications.<Spu>and()
                .eq("online", 1)
                .eq("recommend", Byte.parseByte("1"))
                .build();
//        Example<Spu> example = Example.of(spu);

        Page<Spu> page = spuRepository.findAll(build,pageRequest);
        PagingDozer<Spu, SpuVO> pagingDozer = new PagingDozer<>(page, SpuVO.class);

        return UnifyResponse.buildSuccess(pagingDozer);
    }

}

package com.hlkj.mallclassic.service;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.core.LocalUser;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.model.Collect;
import com.hlkj.mallclassic.model.SpuCollect;
import com.hlkj.mallclassic.repository.CollectRepository;
import com.hlkj.mallclassic.utils.TimeUtils;
import com.hlkj.mallclassic.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.service
 * @ClassName: CollectService
 * @Author: Administrator
 * @Description:
 * @Date: 2022/1/23 14:43
 * @Version: 1.0
 */
@Service
public class CollectService {

    @Autowired
    private CollectRepository collectRepository;
    @Autowired
    private SpuCollectService spuCollectService;

    /**
     * 判断当前商品是否已收藏
     * @param spuId
     * @return
     */
    public boolean isCollected(String spuId) {
        String userId = LocalUser.getUser().getId();
        Integer count = collectRepository.countBySpuIdEqualsAndUserIdEquals(spuId, userId);
        return count>0 ? true:false;
    }

    /**
     * 添加收藏
     * @param collect
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public UnifyResponse add(Collect collect){
        UnifyResponse unifyResponse = null;

        Timestamp currTimeStamp = TimeUtils.getCurrTimeStamp();
        //1、收藏数据入库
        Example<Collect> example = Example.of(collect);
        Optional<Collect> optional = collectRepository.findOne(example);
        if (optional.isPresent()){
            return UnifyResponse.buildSuccess();
        }


        collect.setId(UUIDUtils.generateUUID());
        collect.setUserId(LocalUser.getUser().getId());
        collect.setCreateTime(currTimeStamp);
        Collect saveC = collectRepository.save(collect);
        //2、判断spu是否已经被收藏过
        Long count = spuCollectService.getSpuCollectCount(collect.getSpuId());
        if (count > 0) {//增加spu收藏次数
            spuCollectService.increaseCollectCount(collect.getSpuId());
        }else{//新增
            SpuCollect spuCollect = new SpuCollect();
            spuCollect.setId(UUIDUtils.generateUUID());
            spuCollect.setCount(1L);
            spuCollect.setSpuId(collect.getSpuId());
            spuCollect.setSpuName(collect.getSpuName());
            spuCollect.setCreateTime(currTimeStamp);

            spuCollectService.insertSpuCollect(spuCollect);
        }

        unifyResponse = UnifyResponse.buildSuccess(saveC);
        return unifyResponse;
    }

    /**
     * 取消收藏
     * @param spuId
     * @return
     */
    public UnifyResponse remove(String spuId){
        UnifyResponse unifyResponse = null;

        //同一个商品同一个人只能收藏一次
        collectRepository.removeBySpuIdAndUserId(spuId, LocalUser.getUser().getId());

        unifyResponse = UnifyResponse.buildSuccess();
        return unifyResponse;
    }

    public UnifyResponse listPageSort(Integer pageNum, Integer pageSize) {

        UnifyResponse unifyResponse = null;

        if(null==pageNum || null==pageSize){
            throw new APIParamException();
        }
        //1、构建PageRequest对象
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize,Sort.by("createTime").descending());
        //2、查询
        Page<Collect> page = collectRepository.findAll(pageRequest);
        if(ListUtils.isEmpty(page.getContent())){
            throw new NotFoundException();
        }

        List<Collect> list = page.getContent();
        // TODO: 2022/1/24 由于收藏次数表是后加的，这里在循环中查询。后期建议修改以减少数据库查询次数。
        list.forEach(collect -> {
            String spuId = collect.getSpuId();
            Long count = spuCollectService.getSpuCollectCount(spuId);
            collect.setCollectCount(count);
        });

        unifyResponse = UnifyResponse.buildSuccess(page);
        //返回vo列表给前端
        return unifyResponse;
    }

}

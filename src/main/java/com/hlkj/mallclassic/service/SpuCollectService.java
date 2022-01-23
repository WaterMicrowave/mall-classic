package com.hlkj.mallclassic.service;

import com.hlkj.mallclassic.model.SpuCollect;
import com.hlkj.mallclassic.repository.SpuCollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Spu收藏次数
 */
@Service
public class SpuCollectService {

    @Autowired
    private SpuCollectRepository spuCollectRepository;

    /**
     * 查询商品收藏次数
     * @param spuId
     * @return
     */
    public Long getSpuCollectCount(String spuId){
        SpuCollect spuCollect = new SpuCollect();
        spuCollect.setSpuId(spuId);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("count");
        Example<SpuCollect> example = Example.of(spuCollect, matcher);
        Optional<SpuCollect> optional = spuCollectRepository.findOne(example);

        return optional.isPresent() ? optional.get().getCount(): 0;
    }

    public boolean increaseCollectCount(String spuId) {
        int i = spuCollectRepository.increaseCount(spuId);
        return i>0 ? true:false;
    }

    public SpuCollect insertSpuCollect(SpuCollect spuCollect) {
        SpuCollect save = spuCollectRepository.save(spuCollect);
        return save;
    }

}

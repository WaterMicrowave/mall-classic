package com.hlkj.mallclassic.service;

import com.hlkj.mallclassic.model.SpuDetailImg;
import com.hlkj.mallclassic.repository.SpuDetailImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuDetailImgService {

    @Autowired
    private SpuDetailImgRepository spuDetailImgRepository;

    public List<SpuDetailImg> getById(String spuId){

        List<SpuDetailImg> spuDetailImgList = spuDetailImgRepository.findBySpuIdEquals(spuId);
        return spuDetailImgList;
    }

}

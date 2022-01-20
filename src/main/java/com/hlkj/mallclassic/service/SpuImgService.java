package com.hlkj.mallclassic.service;

import com.hlkj.mallclassic.model.SpuImg;
import com.hlkj.mallclassic.repository.SpuImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuImgService {

    @Autowired
    private SpuImgRepository spuImgRepository;

    public List<SpuImg> getById(String spuId){

        List<SpuImg> spuImgList = spuImgRepository.findBySpuIdEquals(spuId);
        return spuImgList;
    }

}

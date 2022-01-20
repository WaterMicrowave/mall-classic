package com.hlkj.mallclassic.repository;


import com.hlkj.mallclassic.model.SpuDetailImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SpuDetailImgRepository extends JpaRepository<SpuDetailImg, String> {

    List<SpuDetailImg> findBySpuIdEquals(String spuId);
}

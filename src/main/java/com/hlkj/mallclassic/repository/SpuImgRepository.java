package com.hlkj.mallclassic.repository;


import com.hlkj.mallclassic.model.SpuImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SpuImgRepository extends JpaRepository<SpuImg, String> {
    List<SpuImg> findBySpuIdEquals(String spuId);
}

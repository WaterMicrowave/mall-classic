package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.SpuCollect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface SpuCollectRepository extends JpaRepository<SpuCollect, String> {

    @Modifying
    @Query(value = "update spu_collect sc set sc.count = sc.count+1 where sc.spu_id = :spuId", nativeQuery = true)
    int increaseCount(String spuId);
}

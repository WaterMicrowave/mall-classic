package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CollectRepository extends JpaRepository<Collect, String> {

    Integer countBySpuIdEqualsAndUserIdEquals(String spuId, String userId);

    @Transactional
    Integer removeBySpuIdAndUserId(String spuId, String userId);

}

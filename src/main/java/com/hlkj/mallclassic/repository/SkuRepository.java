package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.Sku;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.repository
 * @ClassName: BannerRepository
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:41
 * @Version: 1.0
 */
@Where(clause = "online=1 and delete_time=null")
public interface SkuRepository extends JpaRepository<Sku, String> {

    List<Sku> findAllByIdIn(List<String> ids);

    List<Sku> findBySpuIdEquals(String spuId);

    //库存扣减
    @Modifying
    @Query("update Sku s \n" +
            "set s.stock = s.stock-:count\n" +
            "where s.id=:id\n" +
            "and s.stock >= :count")
    int stockReduce(String id, Integer count);

    //库存归还
    @Modifying
    @Query("update Sku s \n" +
            "set s.stock = s.stock+:count\n" +
            "where s.id=:id\n")
    int stockGiveBack(String id, Integer count);
}

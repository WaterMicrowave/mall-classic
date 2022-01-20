package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.Spu;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
public interface SpuRepository extends JpaRepository<Spu, String>, JpaSpecificationExecutor<Spu> {

    List<Spu> findAllByIdIn(List<String> ids);

    @Query(nativeQuery = true, value = "select * from spu where category_id=:cId and online=1 order by id desc limit 0,4")
    List<Spu> findByCategoryIdTop4(@Param("cId") String cId);

    //库存扣减
    @Modifying
    @Query("update Spu s \n" +
            "set s.stock = s.stock-:count\n" +
            "where s.id=:id\n" +
            "and s.stock >= :count")
    int stockReduce(String id, Integer count);

    //库存归还
    @Modifying
    @Query("update Spu s \n" +
            "set s.stock = s.stock+:count\n" +
            "where s.id=:id\n")
    int stockGiveBack(String id, Integer count);
}

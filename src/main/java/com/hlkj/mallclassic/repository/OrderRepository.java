package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.repository
 * @ClassName: BannerRepository
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:41
 * @Version: 1.0
 */
public interface OrderRepository extends JpaRepository<Order, String> {

    //获取用户指定的订单
    Optional<Order> findFirstByUserIdAndId(String uid, String id);

    //查询待支付的订单
    Page<Order> findByStatusAndUserIdOrExpiredTimeAfter(byte status, String uid,Timestamp now,  Pageable pageable);
    //查询所有（不能查询unpaid、cancel）
    Page<Order> findByUserId(String uid, Pageable pageable);
    //按状态查询（不能查询unpaid、cancel）
    Page<Order> findByStatusAndUserId(byte status, String uid, Pageable pageable);
    //查询 已取消 的订单
    @Query("select distinct o\n" +
            "from Order o\n" +
            "where " +
            "o.status = :status\n" +
            "or o.expiredTime < :now\n" +
            "and o.userId = :uid")
    Page<Order> getCanceled(byte status, Timestamp now, String uid, Pageable pageable);



    //查询待支付的订单的数量
    int countAllByExpiredTimeAfterAndStatusAndUserId(Timestamp now, byte status, String uid);
    //按状态查询（不能查询unpaid、cancel）数量
    int countAllByStatusAndUserId(byte status, String uid);
    //查询 已取消 的订单数量
//    @Query("select distinct o\n" +
//            "from Order o\n" +
//            "where " +
//            "o.status = :status\n" +
//            "or o.expiredTime < :now\n" +
//            "and o.userId = :uid")
//    List<Order> getCanceledCount(byte status, Timestamp now, String uid);

    @Transactional
    @Modifying
    @Query("update Order o\n" +
            "set o.status = :status\n" +
            "where o.id = :id\n" +
            "and o.userId = :uid\n")
    int updateUserSpecialStatus(Byte status, String id, String uid);

    //确认收货
    @Transactional
    @Modifying
    @Query("update Order o\n" +
            "set o.status = :status\n" +
            "where o.id = :oid\n" +
            "and o.orderNo = :orderNo\n" +
            "and o.userId = :uid\n")
    int deliveryConfirm(Byte status, String oid, String orderNo, String uid);
}

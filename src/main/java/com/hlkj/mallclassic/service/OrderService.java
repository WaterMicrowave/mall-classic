package com.hlkj.mallclassic.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hlkj.mallclassic.bo.OrderBO;
import com.hlkj.mallclassic.common.PagingDozer;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.core.LocalUser;
import com.hlkj.mallclassic.core.money.IMoneyDiscount;
import com.hlkj.mallclassic.dto.OrderDTO;
import com.hlkj.mallclassic.dto.SkuInfoDTO;
import com.hlkj.mallclassic.enumerate.CouponStatus;
import com.hlkj.mallclassic.enumerate.OrderStatus;
import com.hlkj.mallclassic.exception.*;
import com.hlkj.mallclassic.logic.CouponChecker;
import com.hlkj.mallclassic.logic.OrderChecker;
import com.hlkj.mallclassic.model.*;
import com.hlkj.mallclassic.repository.*;
import com.hlkj.mallclassic.utils.OrderUtils;
import com.hlkj.mallclassic.utils.UUIDUtils;
import com.hlkj.mallclassic.vo.OrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @program: mall-classic
 * @description: 订单service
 * @author: 李向平
 * @create: 2021-03-21 11:17
 */
@Service
public class OrderService {

    @Autowired
    private SpuService spuService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private UserCouponRepository userCouponRepository;
    @Autowired
    private IMoneyDiscount iMoneyDiscount;//金额计算器
    @Autowired
    private CouponCategoryRepository couponCategoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SpuRepository spuRepository;
    @Autowired
    private SkuRepository skuRepository;

    private static int payTimeLimit;

    @Value("${qfk.pay-limit-time}")
    public void setPayTimeLimit(int payTimeLimit) {
        OrderService.payTimeLimit = payTimeLimit;
    }

    /**
     * 确认收货
     * @param orderId
     * @param orderNo
     * @return
     */
    public Integer deliveryConfirm(String orderId, String orderNo){
        return orderRepository.deliveryConfirm(Byte.parseByte(OrderStatus.FINISHED.getValue().toString()),
                orderId, orderNo, LocalUser.getUser().getId());
    }


    @Transactional//由于下单业务需要同时操作多张数据表，所以需要加事务
    public String orderPlace(String uid, OrderDTO orderDTO,OrderChecker orderChecker){
        String orderId = UUIDUtils.generateUUID();
        //生成订单号
        String orderNo = OrderUtils.generateOrderNo();
        //订单延迟支付过期时间
        Calendar expiredTime = Calendar.getInstance();
        Calendar placedTime = (Calendar) expiredTime.clone();
        expiredTime.add(Calendar.SECOND, OrderService.payTimeLimit);
        //创建oder对象
        Order order = new Order();
        order.setId(orderId);
        order.setOrderNo(orderNo);
        order.setUserId(uid);
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setFinalTotalPrice(orderDTO.getFinalTotalPrice());
        order.setTotalCount(orderChecker.getSkuList().size()+orderChecker.getSpuList().size());
        order.setSnapImg(orderChecker.getLeaderImg());
        order.setSnapTitle(orderChecker.getLeaderTitle());
        order.setSnapAddress(JSON.toJSONString(orderDTO.getOrderAddressDTO()));
        order.setStatus(Byte.parseByte(OrderStatus.UNPAID.getValue().toString()));
        order.setSnapItems(JSON.toJSONString(orderDTO.getSkuInfoList()));
        order.setPlacedTime(new Timestamp(placedTime.getTime().getTime()));//不让数据库自动生成——保证延时精度
        order.setExpiredTime(new Timestamp(expiredTime.getTime().getTime()));
        //订单入库
        orderRepository.save(order);
        //减除库存
        this.reduceStock(orderDTO,orderChecker);
        //优惠券核销()
        String couponId = orderDTO.getCouponId();
        if(!StringUtils.isEmpty(couponId)){
            this.writeOffCoupon(uid,couponId,orderId);
        }
        //加入延迟消息队列（通知进行库存和优惠券归还）
        this.sendToRedis(orderId, uid);
        return orderNo;
    }
    @Autowired
    private StringRedisTemplate stringRedisTemplate;//继承自RedisTemplate https://www.cnblogs.com/slowcity/p/9002660.html
    //技术原因导致下单失败不能抛出异常，资金损失是平台无法接受的
    private void sendToRedis(String orderId, String userId){
        try {
            String key = orderId + "#" + userId;
            stringRedisTemplate.opsForValue().set(key, "1", OrderService.payTimeLimit, TimeUnit.SECONDS);
        } catch (Exception e) {
            //todo 建议加入系统预警（强提醒：CMS、运营人员）
            e.printStackTrace();
        }
    }

    public Integer countUnpaid(){
        return orderRepository.countAllByExpiredTimeAfterAndStatusAndUserId(new Timestamp(new Date().getTime()),
                                                Byte.parseByte(OrderStatus.UNPAID.getValue().toString()),
                                                LocalUser.getUser().getId());
    }
    public Integer countAllStatusAndUserId(Integer status){
        return orderRepository.countAllByStatusAndUserId(Byte.parseByte(status.toString()),
                LocalUser.getUser().getId());
    }
//    public Integer getCanceledCount(){
//        List<Order> orders = orderRepository.getCanceledCount(Byte.parseByte(OrderStatus.CANCELED.getValue().toString()),
//                                                new Timestamp(new Date().getTime()),
//                                                LocalUser.getUser().getId());
//        return orders.size();
//    }

    /**
     * 监听延时支付操作：修改订单状态为“已过期”、归还库存
     * @param orderId
     * @param userId
     */
    @Transactional
    public void stockGiveBack(String orderId, String userId){
        System.out.println("redis通知开始");
        //1、修改指定用户的指定订单状态为“已取消”
        Optional<Order> optional = orderRepository.findFirstByUserIdAndId(userId, orderId);
        Order order = optional.orElseThrow(NotFoundException::new);
        System.out.println(order);
        System.out.println("执行了归还1");
        int i = orderRepository.updateUserSpecialStatus(Byte.parseByte(OrderStatus.CANCELED.getValue().toString()),
                                                        orderId,
                                                        userId);
        System.out.println("执行了归还2="+i);
        System.out.println(i);
        //2、修改库存信息
        String snapItems = order.getSnapItems();
        List<OrderBO> list = (List<OrderBO>) JSON.parse(snapItems);
        list = JSON.parseObject(snapItems, new TypeReference<ArrayList<OrderBO>>(){});
        //3、修改库存
        if(!ListUtils.isEmpty(list)){
            list.forEach(orderBO -> {
                String id = orderBO.getId();
                Boolean hasSku = orderBO.getHasSku();
                Integer count = orderBO.getCount();
                if(!hasSku){
                    spuRepository.stockGiveBack(id, count);
                }
                else{
                    skuRepository.stockGiveBack(id, count);
                }
            });
        }
    }
    /**
     * 分页查询用户“待支付”的订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    public UnifyResponse unpaidListByPage(Integer pageNum, Integer pageSize){
        String uid = LocalUser.getUser().getId();
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize,Sort.by("createTime").descending());

        Page<Order> page = orderRepository
                .findByStatusAndUserIdOrExpiredTimeAfter(Byte.parseByte(OrderStatus.UNPAID.getValue().toString()),
                                                        uid,
                                                        new Timestamp(new Date().getTime()),
                                                        pageRequest);
        PagingDozer<Order, OrderVO> pagingDozer = new PagingDozer<>(page, OrderVO.class);
        if (pagingDozer.getTotalCount()>0) {
            List<OrderVO> items = pagingDozer.getItems();
            items.forEach(item -> {
                item.setPeriod(OrderService.payTimeLimit);
            });
        }
        return UnifyResponse.buildSuccess(pagingDozer);
    }

    /**
     * 获取 “已取消”的订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    public UnifyResponse canceledListByPage(Integer pageNum, Integer pageSize){
        String uid = LocalUser.getUser().getId();
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize,Sort.by("createTime").descending());

        Page<Order> page = orderRepository
                .getCanceled(Byte.parseByte(OrderStatus.CANCELED.getValue().toString()), new Timestamp(new Date().getTime()),
                        uid,
                        pageRequest);
        PagingDozer<Order, OrderVO> pagingDozer = new PagingDozer<>(page, OrderVO.class);
        if (pagingDozer.getTotalCount()>0) {
            List<OrderVO> items = pagingDozer.getItems();
            items.forEach(item -> item.setPeriod(OrderService.payTimeLimit));
        }
        return UnifyResponse.buildSuccess(pagingDozer);
    }

    /**
     * 根据状态查询（不能查询 “待支付”、“已取消”）
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    public UnifyResponse ListByStatusPage(Integer status, Integer pageNum, Integer pageSize){
        String uid = LocalUser.getUser().getId();
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("createTime").descending());
        Page<Order> page = null;
        if (status==OrderStatus.ALL.getValue()){
            page = orderRepository.findByUserId(uid, pageRequest);
        }
        else {
            page = orderRepository.findByStatusAndUserId(Byte.parseByte(status.toString()), uid, pageRequest);
        }
        PagingDozer<Order, OrderVO> pagingDozer = new PagingDozer<>(page, OrderVO.class);
        if (pagingDozer.getTotalCount()>0) {
            List<OrderVO> items = pagingDozer.getItems();
            items.forEach(item -> item.setPeriod(OrderService.payTimeLimit));
        }
        return UnifyResponse.buildSuccess(pagingDozer);
    }


    private void writeOffCoupon(String uid, String couponId, String orderId){
        int result = userCouponRepository.writeOff(uid, couponId, orderId);
        if (result!=1){
            throw new ForbiddenException();
        }
    }

    //扣减库存
    private void reduceStock(OrderDTO orderDTO, OrderChecker orderChecker){
        List<Spu> spuList = orderChecker.getSpuList();
        List<Sku> skuList = orderChecker.getSkuList();
        List<SkuInfoDTO> skuInfoList = orderDTO.getSkuInfoList();
        for (Spu spu:
             spuList) {
            int count = 0;
            for (SkuInfoDTO skuInfoDTO:
                 skuInfoList) {
                if (skuInfoDTO.getId().equals(spu.getId()) && !skuInfoDTO.getHasSku()){
                    count = skuInfoDTO.getCount();
                }
            }
            int result = spuRepository.stockReduce(spu.getId(), count);
            if (result != 1){
                throw new APIParamException();
            }
        }
        for (Sku sku:
                skuList) {
            int count = 0;
            for (SkuInfoDTO skuInfoDTO:
                    skuInfoList) {
                if (skuInfoDTO.getId().equals(sku.getId()) && skuInfoDTO.getHasSku()){
                    count = skuInfoDTO.getCount();
                }
            }
            int result = skuRepository.stockReduce(sku.getId(), count);
            if (result != 1){
                throw new APIParamException();
            }
        }
    }

    /**
     * 判断是否可以下单
     * @param uid
     * @param orderDTO 前端传递的订单数据
     */
    public OrderChecker isOk(String uid, OrderDTO orderDTO) {
        OrderChecker orderChecker = null;
        //前端提交的订单总价必须为正数
        if(orderDTO.getFinalTotalPrice().compareTo(new BigDecimal("0")) <= 0){
            throw new OrderException(800001);//订单金额不能为0
        }
        /**
         * 组装校验数据
         */
        //获取orderDTO中所有单品spu的id列表
        List<String> spuIdList = orderDTO.getSkuInfoList()
                .stream()
                .filter(skuInfoDTO -> skuInfoDTO.getHasSku().booleanValue()==Boolean.FALSE)
                .map(SkuInfoDTO::getId)
                .collect(Collectors.toList());
        List<Spu> spuList = spuService.getList(spuIdList);
        //获取orderDTO中所有sku的id列表
        List<String> skuIdList = orderDTO.getSkuInfoList()
                .stream()
                .filter(skuInfoDTO -> skuInfoDTO.getHasSku().booleanValue()==Boolean.TRUE)
                .map(SkuInfoDTO::getId)
                .collect(Collectors.toList());
        List<Sku> skuList = skuService.getList(skuIdList);
        /**
         * 开始订单校验
         */
        //3-1优惠券相关校验
        //判断订单是否使用了优惠券
        String couponId = orderDTO.getCouponId();
        //如果使用了就需要进行优惠券校验
        if(!StringUtils.isEmpty(couponId)){
            //3-1-1判断优惠券是否存在
            Coupon coupon = couponRepository.findById(couponId)
                    .orElseThrow(()->new ActivityCouponException(500004));
            //3-1-2判断用户是否领取了当前优惠券
            UserCoupon userCoupon = userCouponRepository.findByUserIdEqualsAndCouponIdEquals(uid, couponId)
                    .orElseThrow(()->new ActivityCouponException(500007)).get(0);
            //3-1-3判断用户是否已使用了当前优惠券
            if (CouponStatus.USED.getValue()==userCoupon.getStatus()) {
                throw new ActivityCouponException(500011);
            }
            //订单选择优惠券可以使用所有分类的id列表
            List<String> effectedCategoryIds = couponCategoryRepository.findIdsByCouponId(couponId);
            CouponChecker couponChecker = new CouponChecker(coupon,effectedCategoryIds,iMoneyDiscount);
            //开始订单校验
            orderChecker = new OrderChecker(orderDTO, spuList, skuList, couponChecker, 5);
            orderChecker.orderVerify();
        }
        else{
            orderChecker = new OrderChecker(orderDTO, spuList, skuList, null, 5);
            orderChecker.orderVerify();
        }
        return orderChecker;
    }

}

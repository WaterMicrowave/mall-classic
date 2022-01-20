package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.annotations.ScopeLevel;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.core.LocalUser;
import com.hlkj.mallclassic.dto.OrderDTO;
import com.hlkj.mallclassic.logic.OrderChecker;
import com.hlkj.mallclassic.service.OrderService;
import com.hlkj.mallclassic.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: mall-classic
 * @description: 订单相关接口
 * @author: 李向平
 * @create: 2021-03-20 10:11
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 下单接口
     * @param orderDTO 下单传递对象
     * @return
     */
    @PostMapping("/place")
    @ScopeLevel
    public UnifyResponse placeOrder(@RequestBody OrderDTO orderDTO){
        String uid = LocalUser.getUser().getId();
        //订单需要校验的参数有哪些？
        //1、无货——大型电商系统，前端下单操作过程中可能出现货被买完的情况
        //2、数量限制——订单购买总数量、sku数量（热销产品或低价商品）
        //3、价格校验——totalPrice、finalTotalPrice
        //4、优惠券——用户是否拥有优惠券、优惠券是否过期
        OrderChecker orderChecker = orderService.isOk(uid, orderDTO);
        //生成订单
        String orderId = orderService.orderPlace(uid, orderDTO, orderChecker);

        return UnifyResponse.buildSuccess("生成订单号："+orderId);
    }

    @RequestMapping("/delivery/confirm")
    @ScopeLevel
    public UnifyResponse deliveryConfirm(@RequestParam String orderId,
                                         @RequestParam String orderNo){
        Integer i = orderService.deliveryConfirm(orderId, orderNo);
        if (i == 1) {
            return UnifyResponse.buildSuccess();
        }
        return UnifyResponse.buildFailed();
    }

    /**
     * 分页查询 待支付 订单
     * @param start
     * @param count
     * @return
     */
    @RequestMapping("/unpaid/list/page")
    @ScopeLevel
    public UnifyResponse unpaidListByPage(@RequestParam Integer start, @RequestParam Integer count){
        return orderService.unpaidListByPage(start, count);
    }
    @RequestMapping("/count/unpaid")
    @ScopeLevel
    public UnifyResponse unpaidCount(){
        return UnifyResponse.buildSuccess(orderService.countUnpaid());
    }

    /**
     * 分页查询 已取消 订单
     * @param start
     * @param count
     * @return
     */
    @RequestMapping("/canceled/list/page")
    @ScopeLevel
    public UnifyResponse canceledListByPage(@RequestParam Integer start, @RequestParam Integer count){
        return orderService.canceledListByPage(start, count);
    }

    /**
     * 根据 订单状态 分页查询 (不能查询“待支付”、“已取消”)
     * @param start
     * @param count
     * @return
     */
    @RequestMapping("/status/list/page")
    @ScopeLevel
    public UnifyResponse unpaidListByPage(@RequestParam Integer status, @RequestParam Integer start, @RequestParam Integer count){
        return orderService.ListByStatusPage(status, start, count);
    }
    @RequestMapping("/count/status")
    @ScopeLevel
    public UnifyResponse statusCount(@RequestParam Integer status){
        return UnifyResponse.buildSuccess(orderService.countAllStatusAndUserId(status));
    }

//    @PostMapping("/verify")
//    @ScopeLevel
//    public UnifyResponse OrderVerify(@RequestBody OrderDTO orderDTO){
//        String uid = LocalUser.getUser().getId();
//        orderService.isOk(uid,orderDTO);
//        return UnifyResponse.buildSuccess();
//    }

}

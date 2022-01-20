package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.service.CartService;
import com.hlkj.mallclassic.vo.CartDataSyncVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: mall-classic
 * @description: 购物车
 * @author: 李向平
 * @create: 2021-03-24 13:27
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/sync")
    public UnifyResponse syncCartData(@RequestParam String cartItemsInfo){
        List<CartDataSyncVO> cartDataSyncVOS = cartService.syncCartData(cartItemsInfo);
        return UnifyResponse.buildSuccess(cartDataSyncVOS);
    }
}

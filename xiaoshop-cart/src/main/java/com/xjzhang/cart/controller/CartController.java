package com.xjzhang.cart.controller;

import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.cart.model.vo.CartItemVO;
import com.xjzhang.cart.model.vo.CartVO;
import com.xjzhang.cart.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/12/26 15:59
 */
@RestController
@RequestMapping(value = "cart/" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "购物车管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CartController {
    @Resource
    private ICartService cartService;

    /**
     * 添加购物车
     */
    @ApiOperation(httpMethod = "POST", value = "添加购物车")
    @RequestMapping("/addCartItem")
    BaseWrapper addCartItem(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        cartService.addCartItem(skuId, num);

        return BaseWrapper.ok();
    }

    /**
     * 添加购物车成功
     */
    @ApiOperation(httpMethod = "POST", value = "添加购物车成功")
    @RequestMapping("/getCartItem")
    BaseWrapper<CartItemVO> addCartItemSuccess(@RequestParam("skuId") Long skuId) {
        CartItemVO cartItemVO = cartService.getCartItem(skuId);
        return BaseWrapper.ok(cartItemVO);
    }

    /**
     * 获得购物车
     */
    @ApiOperation(httpMethod = "POST", value = "获得购物车")
    @RequestMapping("/cart")
    BaseWrapper<CartVO> getCart() {
        CartVO cartVO = cartService.getCart();
        return BaseWrapper.ok(cartVO);
    }

    /**
     * 选中购物车商品
     */
    @ApiOperation(httpMethod = "POST", value = "选中购物车商品")
    @RequestMapping("/checkCart")
    BaseWrapper checkCart(Long skuId, Integer isChecked) {
        cartService.checkCart(skuId, isChecked);
        return BaseWrapper.ok();
    }

    /**
     * 修改购物车中某个商品的数量
     */
    @ApiOperation(httpMethod = "POST", value = "修改购物车中某个商品的数量")
    @RequestMapping("/countItem")
    BaseWrapper changeItemCount(Long skuId, Integer num) {
        cartService.changeItemCount(skuId, num);
        return BaseWrapper.ok();
    }

    /**
     * 删除购物车中某个商品
     */
    @ApiOperation(httpMethod = "POST", value = "删除购物车中某个商品")
    @RequestMapping("/deleteItem")
    BaseWrapper deleteItem(Long skuId) {
        cartService.deleteItem(skuId);

        return BaseWrapper.ok();
    }

    /**
     * 获取购物车中选中的商品
     */
    @ApiOperation(httpMethod = "POST", value = "获取购物车中选中的商品")
    @RequestMapping("/getCheckedItems")
    BaseWrapper<List<CartItemVO>> getCheckedItems() {
        List<CartItemVO> cartItemVOS = cartService.getCheckedItems();

        return BaseWrapper.ok(cartItemVOS);
    }
}

package com.xjzhang.cart.service;

import com.xjzhang.cart.model.vo.CartItemVO;
import com.xjzhang.cart.model.vo.CartVO;

import java.util.List;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2022/1/1 20:02
 */
public interface ICartService {
    CartItemVO addCartItem(Long skuId, Integer num);

    CartItemVO getCartItem(Long skuId);

    CartVO getCart();

    void checkCart(Long skuId, Integer isChecked);

    void changeItemCount(Long skuId, Integer num);

    void deleteItem(Long skuId);

    List<CartItemVO> getCheckedItems();
}

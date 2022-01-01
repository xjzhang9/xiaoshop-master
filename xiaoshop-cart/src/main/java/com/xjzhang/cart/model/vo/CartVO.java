package com.xjzhang.cart.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/12/26 16:00
 */
@Data
public class CartVO {
   private Set<CartItemVO> cartItemVOList;

    /**
     * 商品数量
     */
    private Integer countNum;

    /**
     * 类型数量
     */
    private Integer countType;

    /**
     * 商品总价
     */
    private BigDecimal totalAccount;

    /**
     * 减免价格
     */
    private BigDecimal reduce = new BigDecimal("0.00");
}

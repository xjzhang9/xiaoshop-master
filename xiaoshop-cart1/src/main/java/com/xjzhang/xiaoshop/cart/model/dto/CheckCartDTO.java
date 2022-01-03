package com.xjzhang.xiaoshop.cart.model.dto;

import com.xjzhang.xiaoshop.cart.model.vo.CartItemVO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/12/26 16:00
 */
@Data
public class CheckCartDTO {
   private String skuId;
   private Boolean check;
}

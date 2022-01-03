package com.xjzhang.xiaoshop.cart.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/12/26 16:04
 */
@Data
public class CartItemVO {
    /**
     * 商品skuId
     */
    public Long skuId;

    /**
     * 商品标题
     */
    public String tittle;

    /**
     * 商品默认图片
     */
    public String image;

    /**
     * 商品单价
     */
    public BigDecimal price;

    /**
     * 商品是否被选中
     */
    public Boolean check;

    /**
     * 商品总数量
     */
    public Integer count;

    /**
     * 商品总价格
     */
    public BigDecimal totalPrice;

    /**
     * 商品套餐属性
     */
    public List<String> skuAttrValues;

    /**
     * 添加时间
     */
    public Date createTime;

    /**
     * 当前购物车的总价格等于单价*数量
     * @return
     */
    public BigDecimal getTotalPrice() {
        return  price.multiply(new BigDecimal(count));
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartItemVO)) {
            return false;
        }
        CartItemVO that = (CartItemVO) o;
        return getSkuId().equals(that.getSkuId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSkuId());
    }
}

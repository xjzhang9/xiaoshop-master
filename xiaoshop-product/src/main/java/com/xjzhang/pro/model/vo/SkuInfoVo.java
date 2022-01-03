package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * sku信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Data
@EqualsAndHashCode()
public class SkuInfoVo {
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 所属分类id
     */
    private Long catalogId;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 销量
     */
    private Long saleCount;
    /**
     * 默认图片
     */
    private String skuDefaultImg;
    /**
     * sku介绍描述
     */
    private String skuDesc;
    /**
     * skuId
     */
    private Long skuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 副标题
     */
    private String skuSubtitle;
    /**
     * 标题
     */
    private String skuTitle;
    /**
     * spuId
     */
    private Long spuId;
}

package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * sku信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_sku_info")
public class SkuInfo {
    /**
     * 品牌id
     */
    @TableField("brand_id")
    private Long brandId;
    /**
     * 所属分类id
     */
    @TableField("catalog_id")
    private Long catalogId;
    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;
    /**
     * 销量
     */
    @TableField("sale_count")
    private Long saleCount;
    /**
     * 默认图片
     */
    @TableField("sku_default_img")
    private String skuDefaultImg;
    /**
     * sku介绍描述
     */
    @TableField("sku_desc")
    private String skuDesc;
    /**
     * skuId
     */
    @TableId
    @TableField("sku_id")
    private Long skuId;
    /**
     * sku名称
     */
    @TableField("sku_name")
    private String skuName;
    /**
     * 副标题
     */
    @TableField("sku_subtitle")
    private String skuSubtitle;
    /**
     * 标题
     */
    @TableField("sku_title")
    private String skuTitle;
    /**
     * spuId
     */
    @TableField("spu_id")
    private Long spuId;
}

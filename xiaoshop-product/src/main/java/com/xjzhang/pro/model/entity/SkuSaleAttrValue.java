package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * sku销售属性&值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_sku_sale_attr_value")
public class SkuSaleAttrValue extends BaseEntity {
    /**
    *  attr_id
    */
        @TableId
        @TableField("attr_id")
    private Long attrId;
        /**
    *  销售属性名
    */
        @TableField("attr_name")
    private String attrName;
        /**
    *  顺序
    */
        @TableField("attr_sort")
    private Integer attrSort;
        /**
    *  销售属性值
    */
        @TableField("attr_value")
    private String attrValue;
        /**
    *  id
    */
        @TableField("id")
    private Long id;
        /**
    *  sku_id
    */
        @TableField("sku_id")
    private Long skuId;
    }

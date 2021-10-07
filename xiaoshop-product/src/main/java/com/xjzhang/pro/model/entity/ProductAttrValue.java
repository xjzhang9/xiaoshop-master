package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * spu属性值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_product_attr_value")
public class ProductAttrValue extends BaseEntity {
    /**
    *  属性id
    */
        @TableId
        @TableField("attr_id")
    private Long attrId;
        /**
    *  属性名
    */
        @TableField("attr_name")
    private String attrName;
        /**
    *  顺序
    */
        @TableField("attr_sort")
    private Integer attrSort;
        /**
    *  属性值
    */
        @TableField("attr_value")
    private String attrValue;
        /**
    *  id
    */
        @TableField("id")
    private Long id;
        /**
    *  快速展示【是否展示在介绍上；0-否 1-是】
    */
        @TableField("quick_show")
    private Integer quickShow;
        /**
    *  商品id
    */
        @TableField("spu_id")
    private Long spuId;
    }

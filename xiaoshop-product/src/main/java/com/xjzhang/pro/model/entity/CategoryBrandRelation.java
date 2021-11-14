package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * 品牌分类关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_category_brand_relation")
public class CategoryBrandRelation {
    /**
    *  品牌id
    */
        @TableId
        @TableField("brand_id")
    private Long brandId;
        /**
    *
    */
        @TableField("brand_name")
    private String brandName;
        /**
    *  分类id
    */
        @TableField("catelog_id")
    private Long catelogId;
        /**
    *
    */
        @TableField("catelog_name")
    private String catelogName;
        /**
    *
    */
        @TableField("id")
    private Long id;
    }

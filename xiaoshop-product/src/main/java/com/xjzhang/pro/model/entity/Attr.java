package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

/**
 * 商品属性
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:34
 */

@Data
@TableName("tb_pro_attr")
public class Attr {
    /**
     * 属性id
     */
    @TableId(type = IdType.AUTO)
    @TableField("attr_id")
    private Long attrId;
    /**
     * 属性名
     */
    @TableField("attr_name")
    private String attrName;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    @TableField("attr_type")
    private Integer attrType;
    /**
     * 所属分类
     */
    @TableField("catelog_id")
    private Long catelogId;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @TableField("enable")
    private Long enable;
    /**
     * 属性图标
     */
    @TableField("icon")
    private String icon;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @TableField("search_type")
    private Integer searchType;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    @TableField("show_desc")
    private Integer showDesc;
    /**
     * 可选值列表[用逗号分隔]
     */
    @TableField("value_select")
    private String valueSelect;
}

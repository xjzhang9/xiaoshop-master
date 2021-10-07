package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品属性
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:34
 */
@Data
@EqualsAndHashCode()
public class AttrVo extends BaseVo {
    /**
    *  属性id
    */
    private Long attrId;
    /**
    *  属性名
    */
    private String attrName;
    /**
    *  属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
    */
    private Integer attrType;
    /**
    *  所属分类
    */
    private Long catelogId;
    /**
    *  启用状态[0 - 禁用，1 - 启用]
    */
    private Long enable;
    /**
    *  属性图标
    */
    private String icon;
    /**
    *  是否需要检索[0-不需要，1-需要]
    */
    private Integer searchType;
    /**
    *  快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
    */
    private Integer showDesc;
    /**
    *  可选值列表[用逗号分隔]
    */
    private String valueSelect;
}

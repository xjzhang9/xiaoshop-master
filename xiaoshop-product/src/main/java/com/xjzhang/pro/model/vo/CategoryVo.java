package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseTree;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品三级分类
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Data
@EqualsAndHashCode()
public class CategoryVo extends BaseTree<CategoryVo, Long> {
    /**
    *  分类id
    */
    private Long catId;
    /**
    *  层级
    */
    private Integer catLevel;
    /**
    *  图标地址
    */
    private String icon;
    /**
    *  分类名称
    */
    private String name;
    /**
    *  父分类id
    */
    private Long parentCid;

    private String parentName;

    /**
    *  商品数量
    */
    private Integer productCount;
    /**
    *  计量单位
    */
    private String productUnit;
    /**
    *  是否显示[0-不显示，1显示]
    */
    private Integer showStatus;
    /**
    *  排序
    */
    private Integer sort;

    @Override
    public Long getId() {
        return this.getCatId();
    }

    @Override
    public Long getPid() {
        return this.getParentCid();
    }
}

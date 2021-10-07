package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 商品三级分类
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_category")
public class Category extends BaseEntity {
    /**
     * 分类id
     */
    @TableId
    @TableField("cat_id")
    private Long catId;
    /**
     * 层级
     */
    @TableField("cat_level")
    private Integer catLevel;
    /**
     * 图标地址
     */
    @TableField("icon")
    private String icon;
    /**
     * 分类名称
     */
    @TableField("name")
    private String name;
    /**
     * 父分类id
     */
    @TableField("parent_cid")
    private Long parentCid;
    /**
     * 商品数量
     */
    @TableField("product_count")
    private Integer productCount;
    /**
     * 计量单位
     */
    @TableField("product_unit")
    private String productUnit;
    /**
     * 是否显示[0-不显示，1显示]
     */
    /**
     * 是否显示[0-不显示，1显示]
     */
    @TableLogic(value = "1",delval = "0")
    private Integer showStatus;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Category> children;
}

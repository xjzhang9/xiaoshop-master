package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.QueryDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 商品三级分类
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "商品三级分类Dto")
public class CategoryDto extends QueryDto {
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
    @NotEmpty(message = "分类名称不能为空")
    private String name;

    /**
     *  父分类id
     */
    private Long parentCid;
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
}

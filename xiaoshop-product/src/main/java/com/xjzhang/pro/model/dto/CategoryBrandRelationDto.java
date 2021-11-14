package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌分类关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "品牌分类关联Dto")
public class CategoryBrandRelationDto extends BaseQuery {
    /**
     *  品牌id
     */
    private Long brandId;
    /**
     *
     */
    private String brandName;
    /**
     *  分类id
     */
    private Long catelogId;
    /**
     *
     */
    private String catelogName;
    /**
     *
     */
    private Long id;
}

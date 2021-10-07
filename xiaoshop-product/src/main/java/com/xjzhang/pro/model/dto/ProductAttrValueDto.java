package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.QueryDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * spu属性值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "spu属性值Dto")
public class ProductAttrValueDto extends QueryDto {
    /**
     *  属性id
     */
    private Long attrId;
    /**
     *  属性名
     */
    private String attrName;
    /**
     *  顺序
     */
    private Integer attrSort;
    /**
     *  属性值
     */
    private String attrValue;
    /**
     *  id
     */
    private Long id;
    /**
     *  快速展示【是否展示在介绍上；0-否 1-是】
     */
    private Integer quickShow;
    /**
     *  商品id
     */
    private Long spuId;
}

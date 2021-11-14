package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sku销售属性&值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "sku销售属性&值Dto")
public class SkuSaleAttrValueDto extends BaseQuery {
    /**
     *  attr_id
     */
    private Long attrId;
    /**
     *  销售属性名
     */
    private String attrName;
    /**
     *  顺序
     */
    private Integer attrSort;
    /**
     *  销售属性值
     */
    private String attrValue;
    /**
     *  id
     */
    private Long id;
    /**
     *  sku_id
     */
    private Long skuId;
}

package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sku销售属性&值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Data
@EqualsAndHashCode()
public class SkuSaleAttrValueVo extends BaseVo {
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

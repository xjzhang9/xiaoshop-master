package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sku图片
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "sku图片Dto")
public class SkuImagesDto extends BaseQuery {
    /**
     *  默认图[0 - 不是默认图，1 - 是默认图]
     */
    private Integer defaultImg;
    /**
     *  id
     */
    private Long id;
    /**
     *  排序
     */
    private Integer imgSort;
    /**
     *  图片地址
     */
    private String imgUrl;
    /**
     *  sku_id
     */
    private Long skuId;
}

package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * spu信息介绍
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "spu信息介绍Dto")
public class SpuInfoDescDto extends BaseQuery {
    /**
     *  商品介绍
     */
    private String decript;
    /**
     *  商品id
     */
    private Long spuId;
}

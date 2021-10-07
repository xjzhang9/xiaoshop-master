package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * spu信息介绍
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Data
@EqualsAndHashCode()
public class SpuInfoDescVo extends BaseVo {
    /**
    *  商品介绍
    */
    private String decript;
    /**
    *  商品id
    */
    private Long spuId;
}

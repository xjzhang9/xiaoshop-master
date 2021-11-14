package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * spu信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "spu信息Dto")
public class SpuInfoDto extends BaseQuery {
    /**
     *  品牌id
     */
    private Long brandId;
    /**
     *  所属分类id
     */
    private Long catalogId;
    /**
     *
     */
    private Date createTime;
    /**
     *  商品id
     */
    private Long id;
    /**
     *  上架状态[0 - 下架，1 - 上架]
     */
    private Integer publishStatus;
    /**
     *  商品描述
     */
    private String spuDescription;
    /**
     *  商品名称
     */
    private String spuName;
    /**
     *
     */
    private Date updateTime;
    /**
     *
     */
    private BigDecimal weight;
}

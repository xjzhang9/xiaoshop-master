package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.QueryDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * spu图片
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "spu图片Dto")
public class SpuImagesDto extends QueryDto {
    /**
     *  是否默认图
     */
    private Integer defaultImg;
    /**
     *  id
     */
    private Long id;
    /**
     *  图片名
     */
    private String imgName;
    /**
     *  顺序
     */
    private Integer imgSort;
    /**
     *  图片地址
     */
    private String imgUrl;
    /**
     *  spu_id
     */
    private Long spuId;
}

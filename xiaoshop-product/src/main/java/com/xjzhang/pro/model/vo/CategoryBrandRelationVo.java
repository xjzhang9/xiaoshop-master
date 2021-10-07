package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌分类关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Data
@EqualsAndHashCode()
public class CategoryBrandRelationVo extends BaseVo {
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

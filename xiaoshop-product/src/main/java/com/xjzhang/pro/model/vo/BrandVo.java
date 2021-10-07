package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Data
@EqualsAndHashCode()
public class BrandVo extends BaseVo {
    /**
    *  品牌id
    */
    private Long brandId;
    /**
    *  介绍
    */
    private String descript;
    /**
    *  检索首字母
    */
    private String firstLetter;
    /**
    *  品牌logo地址
    */
    private String logo;
    /**
    *  品牌名
    */
    private String name;
    /**
    *  显示状态[0-不显示；1-显示]
    */
    private Integer showStatus;
    /**
    *  排序
    */
    private Integer sort;
}

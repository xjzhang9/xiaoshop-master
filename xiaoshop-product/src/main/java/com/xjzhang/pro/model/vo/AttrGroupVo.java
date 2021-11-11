package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 属性分组
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Data
@EqualsAndHashCode()
public class AttrGroupVo extends BaseVo {
    /**
    *  分组id
    */
    private Long attrGroupId;
    /**
    *  组名
    */
    private String attrGroupName;
    /**
    *  所属分类id
    */
    private Long catelogId;

    /**
     * 所属分类路径
     */
    private Long[] catelogPath;

    /**
    *  描述
    */
    private String descript;
    /**
    *  组图标
    */
    private String icon;
    /**
    *  排序
    */
    private Integer sort;
}

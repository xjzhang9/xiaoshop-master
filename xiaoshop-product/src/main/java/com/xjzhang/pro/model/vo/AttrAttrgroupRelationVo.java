package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 属性&属性分组关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Data
@EqualsAndHashCode()
public class AttrAttrgroupRelationVo extends BaseVo {
    /**
    *  属性分组id
    */
    private Long attrGroupId;
    /**
    *  属性id
    */
    private Long attrId;
    /**
    *  属性组内排序
    */
    private Integer attrSort;
    /**
    *  id
    */
    private Long id;
}

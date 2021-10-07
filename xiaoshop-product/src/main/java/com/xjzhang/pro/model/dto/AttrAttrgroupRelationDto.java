package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.QueryDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 属性&属性分组关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "属性&属性分组关联Dto")
public class AttrAttrgroupRelationDto extends QueryDto {
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

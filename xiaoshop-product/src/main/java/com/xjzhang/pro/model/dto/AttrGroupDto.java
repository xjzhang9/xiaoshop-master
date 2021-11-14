package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 属性分组
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "属性分组Dto")
public class AttrGroupDto extends BaseQuery {
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

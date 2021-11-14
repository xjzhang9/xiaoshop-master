package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.BaseQuery;
import lombok.Data;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/6 21:11
 */
@Data
public class NoRelationAttrDto extends BaseQuery {
    private Long attrGroupId;
    private String attrName;
}

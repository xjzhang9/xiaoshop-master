package com.xjzhang.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * 查询dto类
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 21:07
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class QueryDto implements Serializable {
    private static final long serialVersionUID = -8140103860135939857L;
    private int pageIndex = 1;
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String orderBy;
}

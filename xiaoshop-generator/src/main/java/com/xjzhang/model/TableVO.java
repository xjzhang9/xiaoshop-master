package com.xjzhang.model;

import lombok.Data;

/**
 * 表结构Vo
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:50
 */
@Data
public class TableVO {
    /**
     * 表名
     */
    private String tableName;

    /**
     * engine
     */
    private String engine;

    /**
     * 表的备注
     */
    private String remarks;

    private String createTime;
}

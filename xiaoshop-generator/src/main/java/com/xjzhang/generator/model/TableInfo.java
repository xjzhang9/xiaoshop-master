package com.xjzhang.generator.model;

import lombok.Data;

/**
 * 表结构实体
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:50
 */
@Data
public class TableInfo {
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
    private String tableComment;

    /**
     * 创建时间
     */
    private String createTime;
}

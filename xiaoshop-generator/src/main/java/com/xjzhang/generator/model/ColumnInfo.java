package com.xjzhang.generator.model;

import lombok.Data;

/**
 * 表列字段实体
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:50
 */
@Data
public class ColumnInfo {
    /**
     * 列字段名称
     */
    private String columnName;

    /**
     * 列字段类型
     */
    private String columnType;

    /**
     * 是否为主键：0-否，1-是
     */
    private String primaryKey;

    /**
     * 属性名
     */
    private String propertyName;

    /**
     * 属性类型
     */
    private String propertyType;

    /**
     *  Auto Increment
     * 自动增长
     */
    private String extra;
}

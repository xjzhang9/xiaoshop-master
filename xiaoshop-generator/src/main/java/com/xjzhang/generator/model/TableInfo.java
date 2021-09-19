package com.xjzhang.generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 表结构实体
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:50
 */
@EqualsAndHashCode(callSuper = false)
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

    @JsonIgnore
    private String className;

    @JsonIgnore
    private String classObjectName;

    /**
     * 创建时间
     */
    private String createTime;

    @JsonIgnore
    private ColumnInfo pk;

    @JsonIgnore
    private List<ColumnInfo> columnInfoList;
}

package com.xjzhang.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.generator.model.ColumnInfo;
import com.xjzhang.generator.model.TableInfo;
import java.util.List;

/**
 * 数据库生成service
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:35
 */
public interface GeneratorService {
    /**
     * 表列表查询
     *
     * @param page
     * @return
     */
    IPage<TableInfo> queryTableListWithPage(Page<?> page);

    /**
     * 根据表名查询列信息
     *
     * @param tableName
     * @return
     */
    List<ColumnInfo> queryColumnList(String tableName);

    /**
     * 根据表名查询表信息
     * @param tableName
     * @return
     */
    TableInfo getTableInfo(String tableName);

    /**
     * 根据表名生成代码
     * @param tableNameList
     * @return
     */
    Boolean codeGenerator(List<String> tableNameList);
}

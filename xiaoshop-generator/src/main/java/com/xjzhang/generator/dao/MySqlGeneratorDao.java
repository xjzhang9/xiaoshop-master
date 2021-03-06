package com.xjzhang.generator.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.generator.model.ColumnInfo;
import com.xjzhang.generator.model.TableInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  mysql数据，dao
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:19
 */
@Mapper
public interface MySqlGeneratorDao {
    /**
     * 表列表查询
     * @param page
     * @return
     */
    IPage<TableInfo> queryTableListWithPage(Page<?> page);

    /**
     * 根据表名查询列信息
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
}

package com.xjzhang.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.generator.dao.MySqlGeneratorDao;
import com.xjzhang.generator.model.ColumnInfo;
import com.xjzhang.generator.model.TableInfo;
import com.xjzhang.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 数据库生成service impl
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:35
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Autowired
    MySqlGeneratorDao generatorDao;

    @Override
    public IPage<TableInfo> queryTableListWithPage(Page<?> page) {
        return generatorDao.queryTableListWithPage(page);
    }

    @Override
    public List<ColumnInfo> queryColumnList(String tableName) {
        return generatorDao.queryColumnList(tableName);
    }

    @Override
    public TableInfo getTableInfo(String tableName) {
        return generatorDao.getTableInfo(tableName);
    }

    @Override
    public Boolean codeGenerator(List<String> tableNameList) {
        return null;
    }
}
 
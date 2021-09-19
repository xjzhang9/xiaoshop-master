package com.xjzhang.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.common.exception.BusinessException;
import com.xjzhang.generator.dao.MySqlGeneratorDao;
import com.xjzhang.generator.model.ColumnInfo;
import com.xjzhang.generator.model.TableInfo;
import com.xjzhang.generator.service.GeneratorService;
import com.xjzhang.generator.utils.GeneratorUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

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
    public byte[] codeGenerator(List<String> tableNameList) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(byteArrayOutputStream);
        tableNameList.forEach(tableName -> {
            TableInfo tableInfo = getTableInfo(tableName);
            List<ColumnInfo> columnInfos = queryColumnList(tableName);
            GeneratorUtil.generatorCode(tableInfo, columnInfos, zip);
        });
        IOUtils.closeQuietly(zip);
        return byteArrayOutputStream.toByteArray();
    }
}
 
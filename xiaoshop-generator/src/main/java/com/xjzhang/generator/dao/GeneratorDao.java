package com.xjzhang.generator.dao;

import com.xjzhang.generator.model.ColumnVO;
import com.xjzhang.generator.model.TableVO;

/**
 *  数据库，dao
 * @author xjzhang
 * @version 1.0
 * @date 2021/8/22 20:18
 */
public interface GeneratorDao {
    TableVO queryTableList();
    TableVO queryTableDetail();
    ColumnVO queryColumnD();
}

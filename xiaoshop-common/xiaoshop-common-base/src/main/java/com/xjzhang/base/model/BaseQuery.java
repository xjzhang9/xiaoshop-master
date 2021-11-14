package com.xjzhang.base.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.xjzhang.base.constant.BaseConstant;
import com.xjzhang.base.xss.SQLFilter;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/13 12:51
 */
@Data
public class BaseQuery {
    private long pageIndex = 1;
    private long pageSize = 10;
    private String order;
    private String orderBy;

    public IPage getPage() {
        return getPage(null, false);
    }

    public  IPage getPage(String defaultOrderField, boolean isAsc) {
       return PageInfo.getPage(this, defaultOrderField, isAsc);
    }
}

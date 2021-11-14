package com.xjzhang.base.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.xjzhang.base.constant.BaseConstant;
import com.xjzhang.base.xss.SQLFilter;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

/**
 * 查询dto类
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/7/12 21:07
 */
public class PageInfo<T> implements IPage<T>{
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 每页记录数
     */
    private long size;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 当前页数
     */
    private long current;
    /**
     * 列表数据
     */
    private List records;

    private PageInfo() {

    }

    /**
     * 分页
     * @param list 数据列表
     * @param total 数据总条数
     * @param size 每页数据条数
     * @param current 当前页
     * @return
     */
    public static IPage page(List list, int total, int size, int current) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.records = list;
        pageInfo.total = total;
        pageInfo.size = size;
        pageInfo.current = current;
        pageInfo.pages = pageInfo.getPages();

        return pageInfo;
    }

    /**
     * 分页
     * @param page
     * @return
     */
    public static IPage page(IPage page) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.records = page.getRecords();
        pageInfo.total = page.getTotal();
        pageInfo.size = page.getSize();
        pageInfo.current = page.getCurrent();
        pageInfo.pages =pageInfo.getPages();

        return pageInfo;
    }

    public static IPage getPage(BaseQuery query) {
        return getPage(query, null, false);
    }

    public static IPage getPage(BaseQuery query, String defaultOrderField, boolean isAsc) {
        //分页参数
        long pageIndex = 1;
        long pageSize = 10;

        if (query != null) {
            pageIndex = query.getPageIndex();
            pageSize = query.getPageSize();
        }

        //分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageIndex, pageSize);

        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject(query.getOrder());
        String order = query.getOrderBy();


        //前端字段排序
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
            if(BaseConstant.ASC.equalsIgnoreCase(order)) {
                return  page.addOrder(OrderItem.asc(orderField));
            }else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if(StringUtils.isBlank(defaultOrderField)){
            return page;
        }

        //默认排序
        if(isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        }else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }

    @Override
    public List<OrderItem> orders() {
        return null;
    }

    @Override
    public List<T> getRecords() {
        return records;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        return null;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public IPage<T> setTotal(long total) {
        return null;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public IPage<T> setSize(long size) {
        return null;
    }

    @Override
    public long getCurrent() {
        return current;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        return null;
    }
}

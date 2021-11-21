package com.xjzhang.es.service;

import com.xjzhang.es.model.SearchParamDto;
import com.xjzhang.es.model.SearchResultVo;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/21 20:42
 */
public interface EsSearchService {
    /**
     * 根据搜索参数，搜索商品
     * @param searchParam 搜索参数
     * @return
     */
    SearchResultVo getSearchResult(SearchParamDto searchParam);
}

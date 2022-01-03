package com.xjzhang.es.service.impl;

import com.xjzhang.es.model.SearchParamDto;
import com.xjzhang.es.model.SearchResultVo;
import com.xjzhang.es.service.EsSearchService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/21 20:42
 */
public class EsSearchServiceImpl implements EsSearchService {
    @Override
    public SearchResultVo getSearchResult(SearchParamDto searchParam) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (StringUtils.isNotBlank(searchParam.getKeyword())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("skuTitle", searchParam.getKeyword()));
        }

        if (searchParam.getCatalog3Id() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("catalogId", searchParam.getCatalog3Id()));
        }

        if (searchParam.getBrandId() != null && searchParam.getBrandId().size() > 0) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("brandId", searchParam.getBrandId()));
        }

        if (searchParam.getHasStock() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hasStock", searchParam.getHasStock()));
        }

       if (StringUtils.isNotBlank(searchParam.getSkuPrice())) {
           QueryBuilder queryBuilder =  QueryBuilders.rangeQuery("skuPrice");
           String[] priceStr = searchParam.getSkuPrice().split("_");

//           if (priceStr)
       }
//       boolQueryBuilder.filter()

        return null;
    }
}

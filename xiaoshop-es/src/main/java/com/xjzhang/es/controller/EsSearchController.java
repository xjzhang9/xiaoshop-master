package com.xjzhang.es.controller;

import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.es.model.SearchParamDto;
import com.xjzhang.es.model.SearchResultVo;
import com.xjzhang.es.service.EsSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/13 14:54
 */
@RestController
public class EsSearchController {
    @Resource
    private EsSearchService searchService;

    @GetMapping(value = "/search")
    public BaseWrapper<SearchResultVo> getSearchPage(SearchParamDto searchParam) {
        SearchResultVo result= searchService.getSearchResult(searchParam);

        return BaseWrapper.ok(result);
    }
}

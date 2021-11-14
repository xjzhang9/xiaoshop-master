package com.xjzhang.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.xjzhang.base.model.es.SkuEsModel;
import com.xjzhang.es.config.ElasticSearchConfig;
import com.xjzhang.es.constant.EsConstant;
import com.xjzhang.es.service.EsSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EsSave Service保存es数据实现类
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/13 15:59
 */
@Slf4j
@Service
public class EsSaveServiceImpl implements EsSaveService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean saveProductAsIndices(List<SkuEsModel> skuEsModels) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel skuEsModel : skuEsModels) {
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuEsModel.getSkuId().toString());
            String jsonString = JSON.toJSONString(skuEsModel);
            indexRequest.source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        BulkResponse responses = restHighLevelClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);
        boolean hasFail = responses.hasFailures();
        List<String> idList = Arrays.stream(responses.getItems()).map(item -> {
            return item.getId();
        }).collect(Collectors.toList());

        log.info("商品上架完成，存入es idList = {}", idList);
        return !hasFail;
    }
}

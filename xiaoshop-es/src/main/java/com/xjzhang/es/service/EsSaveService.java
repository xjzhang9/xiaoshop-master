package com.xjzhang.es.service;

import com.xjzhang.base.model.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * EsSave Service保存es数据类
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/13 15:59
 */
public interface EsSaveService {
    /**
     * 保存sku商品信息到es中
     * @param skuEsModels
     * @return
     */
    boolean saveProductAsIndices(List<SkuEsModel> skuEsModels) throws IOException;
}

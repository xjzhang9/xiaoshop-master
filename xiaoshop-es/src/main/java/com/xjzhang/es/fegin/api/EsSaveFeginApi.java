//package com.xjzhang.es.fegin.api;
//
//import com.xjzhang.base.model.es.SkuEsModel;
//import com.xjzhang.base.wrapper.BaseWrapper;
//import com.xjzhang.es.fegin.hystrix.EsSaveHystrixImpl;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import java.util.List;
//
///**
// * configuration 需要配置token校验, es 保存数据fegin api
// * @author xjzhang
// * @version 1.0
// * @date 2021/11/13 17:11
// */
//@FeignClient(value = "xiaoshop-es", fallback = EsSaveHystrixImpl.class)
//public interface EsSaveFeginApi {
//    /**
//     *  远程保存商品sku信息到es
//     * @param skuEsModels
//     * @return
//     */
//    @PostMapping("/es/saveProductAsIndices")
//    BaseWrapper saveProductAsIndices(@RequestBody List<SkuEsModel> skuEsModels);
//}

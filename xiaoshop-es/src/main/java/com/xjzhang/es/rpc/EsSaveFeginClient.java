//package com.xjzhang.es.rpc;
//
//import com.xjzhang.base.enums.ErrorCodeEnum;
//import com.xjzhang.base.model.es.SkuEsModel;
//import com.xjzhang.base.wrapper.BaseWrapper;
//import com.xjzhang.es.fegin.api.EsSaveFeginApi;
//import com.xjzhang.es.service.EsSaveService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RestController;
//import java.io.IOException;
//import java.util.List;
//
///**
// *  EsSave 对外远程调用接口
// * @author xjzhang
// * @version 1.0
// * @date 2021/11/14 8:52
// */
//@Slf4j
//@RestController
//@Api(value = "API - EsSaveFeginClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//public class EsSaveFeginClient implements EsSaveFeginApi {
//    @Autowired
//    private EsSaveService esSaveService;
//
//    @Override
//    @ApiOperation(httpMethod = "POST", value = "保存上架商品信息到es")
//    public BaseWrapper saveProductAsIndices(List<SkuEsModel> skuEsModels) {
//        boolean ret = false;
//        try {
//            ret = esSaveService.saveProductAsIndices(skuEsModels);
//        } catch (IOException e) {
//            log.error("远程上架商品失败, message = %，data = %", e.getMessage(), skuEsModels);
//        }
//
//        if (ret) {
//            return BaseWrapper.ok();
//        } else {
//            return BaseWrapper.error(ErrorCodeEnum.ES10031001.code(), ErrorCodeEnum.ES10031001.msg());
//        }
//    }
//}

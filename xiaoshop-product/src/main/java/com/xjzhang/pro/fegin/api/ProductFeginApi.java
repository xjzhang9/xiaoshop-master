package com.xjzhang.pro.fegin.api;

import com.xjzhang.base.model.es.SkuEsModel;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.SkuInfoConvert;
import com.xjzhang.pro.convert.SkuSaleAttrValueConvert;
import com.xjzhang.pro.fegin.hystrix.ProductHystrixImpl;
import com.xjzhang.pro.model.entity.SkuInfo;
import com.xjzhang.pro.model.entity.SkuSaleAttrValue;
import com.xjzhang.pro.model.vo.SkuInfoVo;
import com.xjzhang.pro.model.vo.SkuSaleAttrValueVo;
import com.xjzhang.pro.service.SkuInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品信息远程调用提供类
 *
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/13 17:11
 */
@FeignClient(value = "xiaoshop-product", fallback = ProductHystrixImpl.class)
public interface ProductFeginApi {
    /**
     * 根据id获得sku信息
     */
    @ApiOperation(httpMethod = "POST", value = "获取 SkuInfo 信息")
    @RequestMapping("pro/skuinfo/getSkuInfoById")
    BaseWrapper<SkuInfoVo> getSkuInfoById(@PathVariable Long id);

    /**
     * 查询sku销售属性value
     */
    @ApiOperation(httpMethod = "POST", value = "查询 SkuSaleAttrValue 信息列表")
    @RequestMapping("pro/skusaleattrvalue/getSkuSaleAttrValuesAsString")
    BaseWrapper<List<String>> getSkuSaleAttrValuesAsString(@PathVariable Long skuId);
}

package com.xjzhang.pro.client;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.SkuInfoConvert;
import com.xjzhang.pro.model.entity.SkuInfo;
import com.xjzhang.pro.model.vo.SkuInfoVo;
import com.xjzhang.pro.service.SkuInfoService;
import com.xjzhang.pro.service.SkuSaleAttrValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 产品信息远程调用提供类
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/13 17:11
 */
@Slf4j
@RestController
@Api(value = "API - ProductFeginClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductFeginClient  {
    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @ApiOperation(httpMethod = "POST", value = "获取 SkuInfo 信息")
    @RequestMapping("pro/skuinfo/getSkuInfoById/{id}")
    public BaseWrapper<SkuInfoVo> getSkuInfoById(@PathVariable Long id) {
        SkuInfo skuInfo = skuInfoService.getById(id);
        SkuInfoVo skuInfoVo=  SkuInfoConvert.entity2Vo(skuInfo);

        return ResWrapper.ok(skuInfoVo);
    }


    @ApiOperation(httpMethod = "POST", value = "查询 SkuSaleAttrValue 信息列表")
    @RequestMapping("pro/skusaleattrvalue/getSkuSaleAttrValuesAsString/{skuId}")
    public BaseWrapper<List<String>> getSkuSaleAttrValuesAsString(@PathVariable Long skuId) {
        List<String> list =  skuSaleAttrValueService.getSkuSaleAttrValuesAsString(skuId);
        return ResWrapper.ok(list);
    }
}

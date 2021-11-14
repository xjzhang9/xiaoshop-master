package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.SkuSaleAttrValueConvert;
import com.xjzhang.pro.model.dto.SkuSaleAttrValueDto;
import com.xjzhang.pro.model.entity.SkuSaleAttrValue;
import com.xjzhang.pro.model.vo.SkuSaleAttrValueVo;
import com.xjzhang.pro.service.SkuSaleAttrValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

/**
 * sku销售属性&值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/skusaleattrvalue", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "sku销售属性&值管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SkuSaleAttrValueController extends BaseController {
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 查询sku销售属性&值
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 SkuSaleAttrValue 信息")
    @RequestMapping("/querySkuSaleAttrValueWithPage")
    public BaseWrapper<IPage<SkuSaleAttrValueVo>> querySkuSaleAttrValueWithPage(@RequestBody SkuSaleAttrValueDto skuSaleAttrValueDto) {
        IPage<SkuSaleAttrValue> tablePage = skuSaleAttrValueService.page(skuSaleAttrValueDto.getPage());
        IPage<SkuSaleAttrValueVo> voIPage = SkuSaleAttrValueConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得sku销售属性&值
     */
    @ApiOperation(httpMethod = "POST", value = "获取 SkuSaleAttrValue 信息")
    @RequestMapping("/getSkuSaleAttrValueById")
    public BaseWrapper getSkuSaleAttrValueById(@PathVariable Long id) {
        SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueService.getById(id);
        SkuSaleAttrValueVo skuSaleAttrValueVo=  SkuSaleAttrValueConvert.entity2Vo(skuSaleAttrValue);

        return ResWrapper.ok(skuSaleAttrValueVo);
    }

    /**
     * 保存sku销售属性&值
     */
    @PostMapping("/saveSkuSaleAttrValue")
    @ApiOperation(httpMethod = "POST", value = "保存 SkuSaleAttrValue 信息")
    public BaseWrapper saveSkuSaleAttrValue (@RequestBody SkuSaleAttrValueDto  skuSaleAttrValueDto) {
        SkuSaleAttrValue skuSaleAttrValue = SkuSaleAttrValueConvert.dto2Entity(skuSaleAttrValueDto);
        boolean result = skuSaleAttrValueService.save(skuSaleAttrValue);

        return super.handleResult(result);
    }

    /**
     * 更新sku销售属性&值
     */
    @PostMapping("/updateSkuSaleAttrValueById")
    @ApiOperation(httpMethod = "POST", value = "更新SkuSaleAttrValue 信息")
    public BaseWrapper updateSkuSaleAttrValueById (@RequestBody SkuSaleAttrValueDto  skuSaleAttrValueDto) {
        SkuSaleAttrValue skuSaleAttrValue = SkuSaleAttrValueConvert.dto2Entity(skuSaleAttrValueDto);
        boolean result =  skuSaleAttrValueService.updateById(skuSaleAttrValue);

        return super.handleResult(result);
    }

    /**
     * 根据id删除sku销售属性&值
     */
    @PostMapping("/deleteSkuSaleAttrValueById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除SkuSaleAttrValue 信息")
    public BaseWrapper deleteSkuSaleAttrValueById(@PathVariable Long id) {
        boolean result = skuSaleAttrValueService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除sku销售属性&值
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除SkuSaleAttrValue 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] AttrIds){
        boolean result = skuSaleAttrValueService.removeByIds(Arrays.asList(AttrIds));

        return super.handleResult(result);
    }
}

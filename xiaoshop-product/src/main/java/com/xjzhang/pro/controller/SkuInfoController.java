package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.SkuInfoConvert;
import com.xjzhang.pro.model.entity.SkuInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.xjzhang.pro.service.SkuInfoService;
import com.xjzhang.pro.model.dto.SkuInfoDto;
import com.xjzhang.pro.model.vo.SkuInfoVo;

/**
 * sku信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/skuinfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "sku信息管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SkuInfoController extends BaseController {
    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 查询sku信息
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 SkuInfo 信息")
    @RequestMapping("/querySkuInfoWithPage")
    public BaseWrapper<IPage<SkuInfoVo>> querySkuInfoWithPage(@RequestBody SkuInfoDto  skuInfoDto) {
        Page<SkuInfo> queryDtoPage = new Page(skuInfoDto.getPageIndex(), skuInfoDto.getPageSize());
        IPage<SkuInfo> tablePage = skuInfoService.page(queryDtoPage);
        IPage<SkuInfoVo> voIPage = SkuInfoConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得sku信息
     */
    @ApiOperation(httpMethod = "POST", value = "获取 SkuInfo 信息")
    @RequestMapping("/getSkuInfoById")
    public BaseWrapper getSkuInfoById(@PathVariable Long id) {
        SkuInfo skuInfo = skuInfoService.getById(id);
        SkuInfoVo skuInfoVo=  SkuInfoConvert.entity2Vo(skuInfo);

        return ResWrapper.ok(skuInfoVo);
    }

    /**
     * 保存sku信息
     */
    @PostMapping("/saveSkuInfo")
    @ApiOperation(httpMethod = "POST", value = "保存 SkuInfo 信息")
    public BaseWrapper saveSkuInfo (@RequestBody SkuInfoDto  skuInfoDto) {
        SkuInfo skuInfo = SkuInfoConvert.dto2Entity(skuInfoDto);
        boolean result = skuInfoService.save(skuInfo);

        return super.handleResult(result);
    }

    /**
     * 更新sku信息
     */
    @PostMapping("/updateSkuInfoById")
    @ApiOperation(httpMethod = "POST", value = "更新SkuInfo 信息")
    public BaseWrapper updateSkuInfoById (@RequestBody SkuInfoDto  skuInfoDto) {
        SkuInfo skuInfo = SkuInfoConvert.dto2Entity(skuInfoDto);
        boolean result =  skuInfoService.updateById(skuInfo);

        return super.handleResult(result);
    }

    /**
     * 根据id删除sku信息
     */
    @PostMapping("/deleteSkuInfoById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除SkuInfo 信息")
    public BaseWrapper deleteSkuInfoById(@PathVariable Long id) {
        boolean result = skuInfoService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除sku信息
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除SkuInfo 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] BrandIds){
        boolean result = skuInfoService.removeByIds(Arrays.asList(BrandIds));

        return super.handleResult(result);
    }
}

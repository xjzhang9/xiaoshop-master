package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.SkuImagesConvert;
import com.xjzhang.pro.model.entity.SkuImages;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.xjzhang.pro.service.SkuImagesService;
import com.xjzhang.pro.model.dto.SkuImagesDto;
import com.xjzhang.pro.model.vo.SkuImagesVo;

/**
 * sku图片
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/skuimages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "sku图片管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SkuImagesController extends BaseController {
    @Autowired
    private SkuImagesService skuImagesService;

    /**
     * 查询sku图片
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 SkuImages 信息")
    @RequestMapping("/querySkuImagesWithPage")
    public BaseWrapper<IPage<SkuImagesVo>> querySkuImagesWithPage(@RequestBody SkuImagesDto  skuImagesDto) {
        Page<SkuImages> queryDtoPage = new Page(skuImagesDto.getPageIndex(), skuImagesDto.getPageSize());
        IPage<SkuImages> tablePage = skuImagesService.page(queryDtoPage);
        IPage<SkuImagesVo> voIPage = SkuImagesConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得sku图片
     */
    @ApiOperation(httpMethod = "POST", value = "获取 SkuImages 信息")
    @RequestMapping("/getSkuImagesById")
    public BaseWrapper getSkuImagesById(@PathVariable Long id) {
        SkuImages skuImages = skuImagesService.getById(id);
        SkuImagesVo skuImagesVo=  SkuImagesConvert.entity2Vo(skuImages);

        return ResWrapper.ok(skuImagesVo);
    }

    /**
     * 保存sku图片
     */
    @PostMapping("/saveSkuImages")
    @ApiOperation(httpMethod = "POST", value = "保存 SkuImages 信息")
    public BaseWrapper saveSkuImages (@RequestBody SkuImagesDto  skuImagesDto) {
        SkuImages skuImages = SkuImagesConvert.dto2Entity(skuImagesDto);
        boolean result = skuImagesService.save(skuImages);

        return super.handleResult(result);
    }

    /**
     * 更新sku图片
     */
    @PostMapping("/updateSkuImagesById")
    @ApiOperation(httpMethod = "POST", value = "更新SkuImages 信息")
    public BaseWrapper updateSkuImagesById (@RequestBody SkuImagesDto  skuImagesDto) {
        SkuImages skuImages = SkuImagesConvert.dto2Entity(skuImagesDto);
        boolean result =  skuImagesService.updateById(skuImages);

        return super.handleResult(result);
    }

    /**
     * 根据id删除sku图片
     */
    @PostMapping("/deleteSkuImagesById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除SkuImages 信息")
    public BaseWrapper deleteSkuImagesById(@PathVariable Long id) {
        boolean result = skuImagesService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除sku图片
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除SkuImages 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Integer[] DefaultImgs){
        boolean result = skuImagesService.removeByIds(Arrays.asList(DefaultImgs));

        return super.handleResult(result);
    }
}

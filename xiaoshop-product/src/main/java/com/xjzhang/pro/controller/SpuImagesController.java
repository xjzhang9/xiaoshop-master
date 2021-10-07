package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.SpuImagesConvert;
import com.xjzhang.pro.model.entity.SpuInfo;
import com.xjzhang.pro.model.entity.SpuImages;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.xjzhang.pro.service.SpuImagesService;
import com.xjzhang.pro.model.dto.SpuImagesDto;

/**
 * spu图片
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/spuimages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "spu图片管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpuImagesController extends BaseController {
    @Autowired
    private SpuImagesService spuImagesService;

    /**
     * 查询spu图片
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 SpuImages 信息")
    @RequestMapping("/querySpuImagesWithPage")
    public BaseWrapper<IPage<SpuInfo.SpuImagesVo>> querySpuImagesWithPage(@RequestBody SpuImagesDto  spuImagesDto) {
        Page<SpuImages> queryDtoPage = new Page(spuImagesDto.getPageIndex(), spuImagesDto.getPageSize());
        IPage<SpuImages> tablePage = spuImagesService.page(queryDtoPage);
        IPage<SpuInfo.SpuImagesVo> voIPage = SpuImagesConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得spu图片
     */
    @ApiOperation(httpMethod = "POST", value = "获取 SpuImages 信息")
    @RequestMapping("/getSpuImagesById")
    public BaseWrapper getSpuImagesById(@PathVariable Long id) {
        SpuImages spuImages = spuImagesService.getById(id);
        SpuInfo.SpuImagesVo spuImagesVo=  SpuImagesConvert.entity2Vo(spuImages);

        return ResWrapper.ok(spuImagesVo);
    }

    /**
     * 保存spu图片
     */
    @PostMapping("/saveSpuImages")
    @ApiOperation(httpMethod = "POST", value = "保存 SpuImages 信息")
    public BaseWrapper saveSpuImages (@RequestBody SpuImagesDto  spuImagesDto) {
        SpuImages spuImages = SpuImagesConvert.dto2Entity(spuImagesDto);
        boolean result = spuImagesService.save(spuImages);

        return super.handleResult(result);
    }

    /**
     * 更新spu图片
     */
    @PostMapping("/updateSpuImagesById")
    @ApiOperation(httpMethod = "POST", value = "更新SpuImages 信息")
    public BaseWrapper updateSpuImagesById (@RequestBody SpuImagesDto  spuImagesDto) {
        SpuImages spuImages = SpuImagesConvert.dto2Entity(spuImagesDto);
        boolean result =  spuImagesService.updateById(spuImages);

        return super.handleResult(result);
    }

    /**
     * 根据id删除spu图片
     */
    @PostMapping("/deleteSpuImagesById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除SpuImages 信息")
    public BaseWrapper deleteSpuImagesById(@PathVariable Long id) {
        boolean result = spuImagesService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除spu图片
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除SpuImages 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Integer[] DefaultImgs){
        boolean result = spuImagesService.removeByIds(Arrays.asList(DefaultImgs));

        return super.handleResult(result);
    }
}

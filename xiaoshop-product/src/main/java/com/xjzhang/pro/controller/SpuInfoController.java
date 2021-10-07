package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.SpuInfoConvert;
import com.xjzhang.pro.model.entity.SpuInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.xjzhang.pro.service.SpuInfoService;
import com.xjzhang.pro.model.dto.SpuInfoDto;
import com.xjzhang.pro.model.vo.SpuInfoVo;

/**
 * spu信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/spuinfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "spu信息管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpuInfoController extends BaseController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 查询spu信息
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 SpuInfo 信息")
    @RequestMapping("/querySpuInfoWithPage")
    public BaseWrapper<IPage<SpuInfoVo>> querySpuInfoWithPage(@RequestBody SpuInfoDto  spuInfoDto) {
        Page<SpuInfo> queryDtoPage = new Page(spuInfoDto.getPageIndex(), spuInfoDto.getPageSize());
        IPage<SpuInfo> tablePage = spuInfoService.page(queryDtoPage);
        IPage<SpuInfoVo> voIPage = SpuInfoConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得spu信息
     */
    @ApiOperation(httpMethod = "POST", value = "获取 SpuInfo 信息")
    @RequestMapping("/getSpuInfoById")
    public BaseWrapper getSpuInfoById(@PathVariable Long id) {
        SpuInfo spuInfo = spuInfoService.getById(id);
        SpuInfoVo spuInfoVo=  SpuInfoConvert.entity2Vo(spuInfo);

        return ResWrapper.ok(spuInfoVo);
    }

    /**
     * 保存spu信息
     */
    @PostMapping("/saveSpuInfo")
    @ApiOperation(httpMethod = "POST", value = "保存 SpuInfo 信息")
    public BaseWrapper saveSpuInfo (@RequestBody SpuInfoDto  spuInfoDto) {
        SpuInfo spuInfo = SpuInfoConvert.dto2Entity(spuInfoDto);
        boolean result = spuInfoService.save(spuInfo);

        return super.handleResult(result);
    }

    /**
     * 更新spu信息
     */
    @PostMapping("/updateSpuInfoById")
    @ApiOperation(httpMethod = "POST", value = "更新SpuInfo 信息")
    public BaseWrapper updateSpuInfoById (@RequestBody SpuInfoDto  spuInfoDto) {
        SpuInfo spuInfo = SpuInfoConvert.dto2Entity(spuInfoDto);
        boolean result =  spuInfoService.updateById(spuInfo);

        return super.handleResult(result);
    }

    /**
     * 根据id删除spu信息
     */
    @PostMapping("/deleteSpuInfoById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除SpuInfo 信息")
    public BaseWrapper deleteSpuInfoById(@PathVariable Long id) {
        boolean result = spuInfoService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除spu信息
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除SpuInfo 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] BrandIds){
        boolean result = spuInfoService.removeByIds(Arrays.asList(BrandIds));

        return super.handleResult(result);
    }
}

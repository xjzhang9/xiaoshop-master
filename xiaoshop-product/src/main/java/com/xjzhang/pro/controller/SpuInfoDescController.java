package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.SpuInfoDescConvert;
import com.xjzhang.pro.model.dto.SpuInfoDescDto;
import com.xjzhang.pro.model.entity.SpuInfoDesc;
import com.xjzhang.pro.model.vo.SpuInfoDescVo;
import com.xjzhang.pro.service.SpuInfoDescService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

/**
 * spu信息介绍
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/spuinfodesc", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "spu信息介绍管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SpuInfoDescController extends BaseController {
    @Autowired
    private SpuInfoDescService spuInfoDescService;

    /**
     * 查询spu信息介绍
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 SpuInfoDesc 信息")
    @RequestMapping("/querySpuInfoDescWithPage")
    public BaseWrapper<IPage<SpuInfoDescVo>> querySpuInfoDescWithPage(@RequestBody SpuInfoDescDto spuInfoDescDto) {
        IPage<SpuInfoDesc> tablePage = spuInfoDescService.page(spuInfoDescDto.getPage());
        IPage<SpuInfoDescVo> voIPage = SpuInfoDescConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得spu信息介绍
     */
    @ApiOperation(httpMethod = "POST", value = "获取 SpuInfoDesc 信息")
    @RequestMapping("/getSpuInfoDescById")
    public BaseWrapper getSpuInfoDescById(@PathVariable Long id) {
        SpuInfoDesc spuInfoDesc = spuInfoDescService.getById(id);
        SpuInfoDescVo spuInfoDescVo=  SpuInfoDescConvert.entity2Vo(spuInfoDesc);

        return ResWrapper.ok(spuInfoDescVo);
    }

    /**
     * 保存spu信息介绍
     */
    @PostMapping("/saveSpuInfoDesc")
    @ApiOperation(httpMethod = "POST", value = "保存 SpuInfoDesc 信息")
    public BaseWrapper saveSpuInfoDesc (@RequestBody SpuInfoDescDto  spuInfoDescDto) {
        SpuInfoDesc spuInfoDesc = SpuInfoDescConvert.dto2Entity(spuInfoDescDto);
        boolean result = spuInfoDescService.save(spuInfoDesc);

        return super.handleResult(result);
    }

    /**
     * 更新spu信息介绍
     */
    @PostMapping("/updateSpuInfoDescById")
    @ApiOperation(httpMethod = "POST", value = "更新SpuInfoDesc 信息")
    public BaseWrapper updateSpuInfoDescById (@RequestBody SpuInfoDescDto  spuInfoDescDto) {
        SpuInfoDesc spuInfoDesc = SpuInfoDescConvert.dto2Entity(spuInfoDescDto);
        boolean result =  spuInfoDescService.updateById(spuInfoDesc);

        return super.handleResult(result);
    }

    /**
     * 根据id删除spu信息介绍
     */
    @PostMapping("/deleteSpuInfoDescById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除SpuInfoDesc 信息")
    public BaseWrapper deleteSpuInfoDescById(@PathVariable Long id) {
        boolean result = spuInfoDescService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除spu信息介绍
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除SpuInfoDesc 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody String[] Decripts){
        boolean result = spuInfoDescService.removeByIds(Arrays.asList(Decripts));

        return super.handleResult(result);
    }
}

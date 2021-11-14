package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.AttrConvert;
import com.xjzhang.pro.model.dto.AttrDto;
import com.xjzhang.pro.model.entity.Attr;
import com.xjzhang.pro.model.vo.AttrVo;
import com.xjzhang.pro.service.AttrService;
import com.xjzhang.pro.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
/**
 * 商品属性
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:34
 */
@Slf4j
@RestController
@RequestMapping(value = "pro/attr" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "商品属性管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AttrController extends BaseController {
    @Autowired
    private AttrService attrService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询商品属性
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 Attr 信息")
    @RequestMapping("/queryAttrWithPage")
    public BaseWrapper<IPage<AttrVo>> queryAttrWithPage(@RequestBody AttrDto attrDto) {

        IPage<Attr> tablePage = attrService.page(attrDto.getPage());
        IPage<AttrVo> voIPage = AttrConvert.entity2VoPage(tablePage);
        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得商品属性
     */
    @ApiOperation(httpMethod = "POST", value = "获取 Attr 信息")
    @RequestMapping("/getAttrById/{id}")
    public BaseWrapper getAttrById(@PathVariable Long id) {
        Attr attr = attrService.getById(id);
        AttrVo attrVo=  AttrConvert.entity2Vo(attr);
        Long[] catelogPaths = categoryService.getCategoryPath(attrVo.getCatelogId());
        attrVo.setCatelogPath(catelogPaths);
        return ResWrapper.ok(attrVo);
    }


    /**
     * 根据id获得商品属性
     */
    @ApiOperation(httpMethod = "POST", value = "获取 Attr 信息")
    @RequestMapping("/{type}/list/{catId}")
    public BaseWrapper getAttrByCatId(@PathVariable int type, @PathVariable Long catId, @RequestBody AttrDto attrDto) {
        IPage<AttrVo> voIPage = attrService.getAttrByCatId(type, catId, attrDto);
        return ResWrapper.ok(voIPage);
    }

    /**
     * 保存商品属性
     */
    @PostMapping("/saveAttr")
    @ApiOperation(httpMethod = "POST", value = "保存 Attr 信息")
    public BaseWrapper saveAttr (@RequestBody AttrDto  attrDto) {
        Attr attr = AttrConvert.dto2Entity(attrDto);
        boolean result = attrService.save(attr);

        return super.handleResult(result);
    }

    /**
     * 更新商品属性
     */
    @PostMapping("/updateAttrById")
    @ApiOperation(httpMethod = "POST", value = "更新Attr 信息")
    public BaseWrapper updateAttrById (@RequestBody AttrDto  attrDto) {
        Attr attr = AttrConvert.dto2Entity(attrDto);
        boolean result =  attrService.updateById(attr);

        return super.handleResult(result);
    }

    /**
     * 批量删除商品属性
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除Attr 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] AttrIds){
        boolean result = attrService.removeByIds(Arrays.asList(AttrIds));

        return super.handleResult(result);
    }
}

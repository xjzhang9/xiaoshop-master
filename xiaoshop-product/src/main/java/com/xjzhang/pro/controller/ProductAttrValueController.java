package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.ProductAttrValueConvert;
import com.xjzhang.pro.model.entity.ProductAttrValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.xjzhang.pro.service.ProductAttrValueService;
import com.xjzhang.pro.model.dto.ProductAttrValueDto;
import com.xjzhang.pro.model.vo.ProductAttrValueVo;

/**
 * spu属性值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/productattrvalue", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "spu属性值管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductAttrValueController extends BaseController {
    @Autowired
    private ProductAttrValueService productAttrValueService;

    /**
     * 查询spu属性值
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 ProductAttrValue 信息")
    @RequestMapping("/queryProductAttrValueWithPage")
    public BaseWrapper<IPage<ProductAttrValueVo>> queryProductAttrValueWithPage(@RequestBody ProductAttrValueDto  productAttrValueDto) {
        Page<ProductAttrValue> queryDtoPage = new Page(productAttrValueDto.getPageIndex(), productAttrValueDto.getPageSize());
        IPage<ProductAttrValue> tablePage = productAttrValueService.page(queryDtoPage);
        IPage<ProductAttrValueVo> voIPage = ProductAttrValueConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得spu属性值
     */
    @ApiOperation(httpMethod = "POST", value = "获取 ProductAttrValue 信息")
    @RequestMapping("/getProductAttrValueById")
    public BaseWrapper getProductAttrValueById(@PathVariable Long id) {
        ProductAttrValue productAttrValue = productAttrValueService.getById(id);
        ProductAttrValueVo productAttrValueVo=  ProductAttrValueConvert.entity2Vo(productAttrValue);

        return ResWrapper.ok(productAttrValueVo);
    }

    /**
     * 保存spu属性值
     */
    @PostMapping("/saveProductAttrValue")
    @ApiOperation(httpMethod = "POST", value = "保存 ProductAttrValue 信息")
    public BaseWrapper saveProductAttrValue (@RequestBody ProductAttrValueDto  productAttrValueDto) {
        ProductAttrValue productAttrValue = ProductAttrValueConvert.dto2Entity(productAttrValueDto);
        boolean result = productAttrValueService.save(productAttrValue);

        return super.handleResult(result);
    }

    /**
     * 更新spu属性值
     */
    @PostMapping("/updateProductAttrValueById")
    @ApiOperation(httpMethod = "POST", value = "更新ProductAttrValue 信息")
    public BaseWrapper updateProductAttrValueById (@RequestBody ProductAttrValueDto  productAttrValueDto) {
        ProductAttrValue productAttrValue = ProductAttrValueConvert.dto2Entity(productAttrValueDto);
        boolean result =  productAttrValueService.updateById(productAttrValue);

        return super.handleResult(result);
    }

    /**
     * 根据id删除spu属性值
     */
    @PostMapping("/deleteProductAttrValueById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除ProductAttrValue 信息")
    public BaseWrapper deleteProductAttrValueById(@PathVariable Long id) {
        boolean result = productAttrValueService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除spu属性值
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除ProductAttrValue 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] AttrIds){
        boolean result = productAttrValueService.removeByIds(Arrays.asList(AttrIds));

        return super.handleResult(result);
    }
}

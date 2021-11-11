package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.BrandConvert;
import com.xjzhang.pro.model.dto.EditBrandDto;
import com.xjzhang.pro.model.entity.Brand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import com.xjzhang.pro.service.BrandService;
import com.xjzhang.pro.model.dto.BrandDto;
import com.xjzhang.pro.model.vo.BrandVo;

/**
 * 品牌
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Slf4j
@RestController
@RequestMapping(value = "pro/brand", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "品牌管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BrandController extends BaseController {
    @Autowired
    private BrandService brandService;

    /**
     * 查询品牌
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 Brand 信息")
    @RequestMapping("/queryBrandWithPage")
    public BaseWrapper<IPage<BrandVo>> queryBrandWithPage(@RequestBody BrandDto brandDto) {
        Page<Brand> queryDtoPage = new Page(brandDto.getPageIndex(), brandDto.getPageSize());
        IPage<BrandVo> tablePage = brandService.queryBrandWithPage(brandDto, queryDtoPage);
        return ResWrapper.ok(tablePage);
    }

    /**
     * 根据id获得品牌
     */
    @ApiOperation(httpMethod = "POST", value = "获取 Brand 信息")
    @RequestMapping("/getBrandById/{id}")
    public BaseWrapper getBrandById(@PathVariable Long id) {
        log.info("根据Id查询商品品牌信息, brandId={}", id);

        Brand brand = brandService.getById(id);
        BrandVo brandVo = BrandConvert.entity2Vo(brand);

        return ResWrapper.ok(brandVo);
    }

    /**
     * 保存品牌
     */
    @PostMapping("/saveBrand")
    @ApiOperation(httpMethod = "POST", value = "保存 Brand 信息")
    public BaseWrapper saveBrand(@RequestBody @Validated EditBrandDto brandDto) {
        Brand brand = BrandConvert.editDto2Entity(brandDto);
        boolean result = brandService.save(brand);

        return super.handleResult(result);
    }

    /**
     * 更新品牌
     */
    @PostMapping("/updateBrandById")
    @ApiOperation(httpMethod = "POST", value = "更新Brand 信息")
    public BaseWrapper updateBrandById(@RequestBody BrandDto brandDto) {
        log.info("更新商品品牌信息, brandDto={}", brandDto);

        Brand brand = BrandConvert.dto2Entity(brandDto);
        boolean result = brandService.updateBrandById(brand);

        return super.handleResult(result);
    }

    /**
     * 根据id删除品牌
     */
    @PostMapping("/deleteBrandById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除Brand 信息")
    public BaseWrapper deleteBrandById(@PathVariable Long id) {
        log.info("根据id删除品牌信息, id={}", id);

        boolean result = brandService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除品牌
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除Brand 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] brandIds) {
        log.info("根据id删除品牌信息, brandIds={}", brandIds);

        boolean result = brandService.removeByIds(Arrays.asList(brandIds));

        return super.handleResult(result);
    }
}

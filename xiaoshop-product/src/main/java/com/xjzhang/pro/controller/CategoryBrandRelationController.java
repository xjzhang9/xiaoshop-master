package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.CategoryBrandRelationConvert;
import com.xjzhang.pro.model.entity.CategoryBrandRelation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.xjzhang.pro.service.CategoryBrandRelationService;
import com.xjzhang.pro.model.dto.CategoryBrandRelationDto;
import com.xjzhang.pro.model.vo.CategoryBrandRelationVo;

/**
 * 品牌分类关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@RestController
@RequestMapping(value = "pro/categorybrandrelation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "品牌分类关联管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CategoryBrandRelationController extends BaseController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 查询品牌分类关联
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 CategoryBrandRelation 信息")
    @RequestMapping("/queryCategoryBrandRelationWithPage")
    public BaseWrapper<IPage<CategoryBrandRelationVo>> queryCategoryBrandRelationWithPage(@RequestBody CategoryBrandRelationDto  categoryBrandRelationDto) {
        Page<CategoryBrandRelation> queryDtoPage = new Page(categoryBrandRelationDto.getPageIndex(), categoryBrandRelationDto.getPageSize());
        IPage<CategoryBrandRelation> tablePage = categoryBrandRelationService.page(queryDtoPage);
        IPage<CategoryBrandRelationVo> voIPage = CategoryBrandRelationConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 根据id获得品牌分类关联
     */
    @ApiOperation(httpMethod = "POST", value = "获取 CategoryBrandRelation 信息")
    @RequestMapping("/getCategoryBrandRelationById")
    public BaseWrapper getCategoryBrandRelationById(@PathVariable Long id) {
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.getById(id);
        CategoryBrandRelationVo categoryBrandRelationVo=  CategoryBrandRelationConvert.entity2Vo(categoryBrandRelation);

        return ResWrapper.ok(categoryBrandRelationVo);
    }

    /**
     * 保存品牌分类关联
     */
    @PostMapping("/saveCategoryBrandRelation")
    @ApiOperation(httpMethod = "POST", value = "保存 CategoryBrandRelation 信息")
    public BaseWrapper saveCategoryBrandRelation (@RequestBody CategoryBrandRelationDto  categoryBrandRelationDto) {
        CategoryBrandRelation categoryBrandRelation = CategoryBrandRelationConvert.dto2Entity(categoryBrandRelationDto);
        boolean result = categoryBrandRelationService.save(categoryBrandRelation);

        return super.handleResult(result);
    }

    /**
     * 更新品牌分类关联
     */
    @PostMapping("/updateCategoryBrandRelationById")
    @ApiOperation(httpMethod = "POST", value = "更新CategoryBrandRelation 信息")
    public BaseWrapper updateCategoryBrandRelationById (@RequestBody CategoryBrandRelationDto  categoryBrandRelationDto) {
        CategoryBrandRelation categoryBrandRelation = CategoryBrandRelationConvert.dto2Entity(categoryBrandRelationDto);
        boolean result =  categoryBrandRelationService.updateById(categoryBrandRelation);

        return super.handleResult(result);
    }

    /**
     * 根据id删除品牌分类关联
     */
    @PostMapping("/deleteCategoryBrandRelationById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除CategoryBrandRelation 信息")
    public BaseWrapper deleteCategoryBrandRelationById(@PathVariable Long id) {
        boolean result = categoryBrandRelationService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除品牌分类关联
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除CategoryBrandRelation 信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] BrandIds){
        boolean result = categoryBrandRelationService.removeByIds(Arrays.asList(BrandIds));

        return super.handleResult(result);
    }
}

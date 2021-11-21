package com.xjzhang.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.base.BaseController;
import com.xjzhang.base.constant.RedisConstant;
import com.xjzhang.base.model.LoginUserDto;
import com.xjzhang.base.utils.TreeUtils;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import com.xjzhang.pro.convert.CategoryConvert;
import com.xjzhang.pro.model.dto.CategoryDto;
import com.xjzhang.pro.model.entity.Category;
import com.xjzhang.pro.model.vo.CategoryVo;
import com.xjzhang.pro.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 商品三级分类
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Slf4j
@RestController
@RequestMapping(value = "pro/category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "商品三级分类管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CategoryController extends BaseController {
    @Resource
    private CategoryService categoryService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 查询商品三级分类
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 Category 信息")
    @RequestMapping("/list")
    public BaseWrapper<IPage<CategoryVo>> queryCategoryWithPage(@RequestBody CategoryDto categoryDto) {
        IPage<Category> tablePage = categoryService.page(categoryDto.getPage());
        IPage<CategoryVo> voIPage = CategoryConvert.entity2VoPage(tablePage);

        return ResWrapper.ok(voIPage);
    }

    /**
     * 查询商品三级分类
     */
    @ApiOperation(httpMethod = "POST", value = "分页查询 Category 信息")
    @RequestMapping("/tree")
    public BaseWrapper<List<Category>> queryCategoryTreeList() {
        List<Category> tableList = categoryService.list();
        List<Category> tableListTree = new TreeUtils().getChildTreeObjects(CategoryConvert.entity2VoList(tableList), 0L);
        return ResWrapper.ok(tableListTree);
    }

    /**
     * 根据id获得商品三级分类
     */
    @ApiOperation(httpMethod = "POST", value = "获取 Category 信息")
    @RequestMapping("/getCategoryById/{id}")
    public BaseWrapper getCategoryById(@PathVariable Long id) {
        log.info("根据Id查询商品分类信息, categoryId={}", id);

        CategoryVo categoryVo = categoryService.getCategoryById(id);
        return ResWrapper.ok(categoryVo);
    }


    /**
     * 保存商品三级分类
     */
    @PostMapping("/saveCategory")
    @ApiOperation(httpMethod = "POST", value = "保存 Category 信息")
    public BaseWrapper saveCategory(@RequestBody CategoryDto categoryDto) {
        LoginUserDto loginUserDto = getLoginUserDto();
        boolean result = categoryService.saveCategory(categoryDto, loginUserDto);

        return super.handleResult(result);
    }

    @RequestMapping("/update/sort")
    @ApiOperation(httpMethod = "POST", value = "保存 Category 信息")
    public BaseWrapper updateSort(@RequestBody List<CategoryDto> category){

        boolean result  = categoryService.updateBatchById(CategoryConvert.dto2EntityList(category));

        return super.handleResult(result);
    }

    /**
     * 更新商品三级分类
     */
    @PostMapping("/updateCategoryById")
    @ApiOperation(httpMethod = "POST", value = "更新Category 信息")
    public BaseWrapper updateCategoryById(@RequestBody CategoryDto categoryDto) {
        log.info("根据Id更新商品分类信息, categoryDto={}", categoryDto);

        Category category = CategoryConvert.dto2Entity(categoryDto);
        boolean result = categoryService.updateCategoryById(category);
        redisTemplate.delete(RedisConstant.CATEGORY_LIST_KEY);
        return super.handleResult(result);
    }

    /**
     * 根据id删除商品三级分类
     */
    @PostMapping("/deleteCategoryById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除Category 信息")
    public BaseWrapper deleteCategoryById(@PathVariable Long id) {
        log.info("根据Id删除商品分类信息, categoryId={}", id);

        boolean result = categoryService.removeById(id);

        return super.handleResult(result);
    }

    /**
     * 批量删除商品三级分类
     */
    @ApiOperation(httpMethod = "POST", value = "批量删除Category信息")
    @RequestMapping("/bathDelete")
    public BaseWrapper bathDelete(@RequestBody Long[] catIds) {
        log.info("根据Id批量删除商品分类信息, categoryId={}", catIds);

        boolean result = categoryService.removeByIds(Arrays.asList(catIds));
        redisTemplate.delete(RedisConstant.CATEGORY_LIST_KEY);
        return super.handleResult(result);
    }
}

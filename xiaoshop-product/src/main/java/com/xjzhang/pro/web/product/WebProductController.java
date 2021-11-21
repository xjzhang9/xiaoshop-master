package com.xjzhang.pro.web.product;

import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.pro.model.vo.CategoryVo;
import com.xjzhang.pro.model.vo.Catelog2Vo;
import com.xjzhang.pro.model.vo.SkuItemVo;
import com.xjzhang.pro.service.CategoryService;
import com.xjzhang.pro.service.SpuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/20 8:58
 */
@Slf4j
@RequestMapping("/index/")
@RestController
public class WebProductController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 获得商品一级分类
     * @return
     */
    @RequestMapping("/getLevel1Catagories")
    public BaseWrapper<List<CategoryVo>> getLevel1Catagories() {
        List<CategoryVo> categoryVo = categoryService.getLevel1Catagories();
        return BaseWrapper.ok(categoryVo);
    }

    /**
     * 获取商品所有二级分类
     * @return
     */
    @RequestMapping("/getCategoryMap")
    public BaseWrapper<Map<String, List<Catelog2Vo>>> getCategoryMap() {
        Map<String, List<Catelog2Vo>> categoryVo = categoryService.getCategoryMap();
        return BaseWrapper.ok(categoryVo);
    }

    /**
     * 获取商品详情
     * @return
     */
    @RequestMapping("/getItemInfo/{skuId}")
    public BaseWrapper<SkuItemVo> getItemInfo(@PathVariable("skuId") Long skuId) {
        SkuItemVo skuItemVo = spuInfoService.getItemInfo(skuId);
        return BaseWrapper.ok(skuItemVo);
    }


}

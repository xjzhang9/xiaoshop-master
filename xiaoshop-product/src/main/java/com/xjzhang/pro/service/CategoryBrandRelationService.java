package com.xjzhang.pro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.entity.Brand;
import com.xjzhang.pro.model.entity.Category;
import com.xjzhang.pro.model.entity.CategoryBrandRelation;

import java.util.List;

/**
 * 品牌分类关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelation> {
    boolean updateCategory(Category category);
    boolean updateBrand(Long brandId, String name);

    List<Brand> getBrandsByCatId(Long catId);
}

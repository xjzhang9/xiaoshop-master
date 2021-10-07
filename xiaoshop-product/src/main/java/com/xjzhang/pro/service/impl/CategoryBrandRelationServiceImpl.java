package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.CategoryBrandRelationDao;
import com.xjzhang.pro.model.entity.Category;
import com.xjzhang.pro.model.entity.CategoryBrandRelation;
import com.xjzhang.pro.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌分类关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("CategoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelation> implements CategoryBrandRelationService {
    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Override
    public boolean updateCategory(Category category) {
        CategoryBrandRelation categoryBrandRelation = new CategoryBrandRelation();
        categoryBrandRelation.setCatelogId(category.getCatId());
        categoryBrandRelation.setCatelogName(category.getName());

        return this.update(categoryBrandRelation, new UpdateWrapper<CategoryBrandRelation>().lambda().eq(CategoryBrandRelation::getCatelogId, category.getCatId()));
    }

    @Override
    public boolean updateBrand(Long brandId, String name) {
        CategoryBrandRelation categoryBrandRelation = new CategoryBrandRelation();
        categoryBrandRelation.setBrandId(brandId);
        categoryBrandRelation.setBrandName(name);

        return this.update(categoryBrandRelation, new UpdateWrapper<CategoryBrandRelation>().lambda().eq(CategoryBrandRelation::getBrandId, brandId));
    }
}

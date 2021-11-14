package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.CategoryBrandRelationDao;
import com.xjzhang.pro.model.entity.Brand;
import com.xjzhang.pro.model.entity.Category;
import com.xjzhang.pro.model.entity.CategoryBrandRelation;
import com.xjzhang.pro.service.BrandService;
import com.xjzhang.pro.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 品牌分类关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("CategoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelation> implements CategoryBrandRelationService {
    @Resource
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Resource
    private BrandService brandService;

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

    @Override
    public List<Brand> getBrandsByCatId(Long catId) {
        List<CategoryBrandRelation> catelogId = this.list(new QueryWrapper<CategoryBrandRelation>().eq("catelog_id", catId));
        List<Brand> collect = catelogId.stream().map(item -> {
            Long brandId = item.getBrandId();
            Brand byId = brandService.getById(brandId);
            return byId;
        }).collect(Collectors.toList());
        return collect;
    }
}

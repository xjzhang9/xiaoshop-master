package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.base.enums.ErrorCodeEnum;
import com.xjzhang.base.model.LoginUserDto;
import com.xjzhang.pro.convert.CategoryConvert;
import com.xjzhang.pro.dao.CategoryDao;
import com.xjzhang.pro.exception.ProBizException;
import com.xjzhang.pro.model.dto.CategoryDto;
import com.xjzhang.pro.model.entity.Category;
import com.xjzhang.pro.model.vo.CategoryVo;
import com.xjzhang.pro.service.CategoryBrandRelationService;
import com.xjzhang.pro.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品三级分类
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Slf4j
@Service("CategoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryBrandRelationService relationService;

    @Override
    public CategoryVo getCategoryById(Long id) {
        Category category = this.getById(id);
        if (category == null) {
            log.error("找不到分类信息id={}", id);
            throw new ProBizException(ErrorCodeEnum.PRO10021001, id);
        }

        Category parentCategory =  this.getById(category.getParentCid());
        CategoryVo categoryVo = CategoryConvert.entity2Vo(category);
        if (parentCategory  != null) {
            categoryVo.setParentName(parentCategory.getName());
        }

        return categoryVo;
    }

    @Override
    public boolean saveCategory(CategoryDto categoryDto, LoginUserDto loginUserDto) {
        Category parentCategory = this.getById(categoryDto.getParentCid());

        if (parentCategory == null) {
            throw new ProBizException(ErrorCodeEnum.PRO10021002, categoryDto.getParentCid());
        }

        Category category = CategoryConvert.dto2Entity(categoryDto);
//        category.setUpdateInfo(loginUserDto);
        return this.save(category);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "category", allEntries = true)
    @Override
    public boolean updateCategoryById(Category category) {
        this.updateById(category);
        if (StringUtils.isNotBlank(category.getName())) {
            relationService.updateCategory(category);
        }
        return true;
    }
}

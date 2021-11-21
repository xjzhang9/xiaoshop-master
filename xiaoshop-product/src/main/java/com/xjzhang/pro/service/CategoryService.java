package com.xjzhang.pro.service;

import com.xjzhang.base.model.LoginUserDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.dto.CategoryDto;
import com.xjzhang.pro.model.entity.Category;
import com.xjzhang.pro.model.vo.Catelog2Vo;
import com.xjzhang.pro.model.vo.CategoryVo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
public interface CategoryService extends IService<Category> {
    /**
     * 通过id获取商品分类信息
     * @param id
     * @return
     */
    CategoryVo getCategoryById(Long id);

    /**
     * 保存商品分类
     * @param categoryDto
     * @param loginUserDto
     * @return
     */
    boolean saveCategory(CategoryDto categoryDto, LoginUserDto loginUserDto);

    /**
     * 更新商品分类
     * @param category
     * @return
     */
    boolean updateCategoryById(Category category);

    /**
     * 通过id获取商品分类路径
     * @param id
     * @return
     */
    Long[] getCategoryPath(Long id);

    /**
     * 获取所有一级分类
     * @return
     */
    List<CategoryVo> getLevel1Catagories();


    /**
     * 查询所有的二级和三级分类
     *
     * @return
     */
   Map<String, List<Catelog2Vo>> getCategoryMap();
}

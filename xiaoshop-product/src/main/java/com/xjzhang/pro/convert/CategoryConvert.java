package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.pro.model.dto.CategoryDto;
import com.xjzhang.pro.model.entity.Category;
import com.xjzhang.pro.model.vo.CategoryVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;
/**
 * 商品三级分类 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class CategoryConvert {

    /**
     * entity -> vo
     *
     * @return
     */
    public static CategoryVo entity2Vo(Category category) {
        if (category == null) {
            return null;
        }
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);

        return categoryVo;
    }

    /**
     * dto -> entity
     *
     * @return
     */
    public static Category dto2Entity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);

        return category;
    }


    /**
     * entityList -> VoList
     *
     * @return
     */
    public static List<CategoryVo> entity2VoList(List<Category> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<CategoryVo> listVo = new ArrayList<CategoryVo>();
        for (Category item : list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
     * dtoList -> EntityList
     *
     * @return
     */
    public static List<Category> dto2EntityList(List<CategoryDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<Category> listEntity = new ArrayList<Category>();
        for (CategoryDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
     * entityPage -> VoPage
     *
     * @return
     */
    public static IPage<CategoryVo> entity2VoPage(IPage<Category> page) {
        IPage<CategoryVo> vs = page.convert(item -> {
            try {
                return entity2Vo(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        return vs;
    }
}

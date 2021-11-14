package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.pro.model.dto.CategoryBrandRelationDto;
import com.xjzhang.pro.model.entity.CategoryBrandRelation;
import com.xjzhang.pro.model.vo.CategoryBrandRelationVo;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;

/**
 * 品牌分类关联 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class CategoryBrandRelationConvert {

    /**
    * entity -> vo
    * @return
    */
    public static CategoryBrandRelationVo entity2Vo(CategoryBrandRelation categoryBrandRelation) {
        if (categoryBrandRelation ==null){
            return null;
        }
            CategoryBrandRelationVo  categoryBrandRelationVo = new  CategoryBrandRelationVo();
        BeanUtils.copyProperties(categoryBrandRelation, categoryBrandRelationVo);

        return categoryBrandRelationVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static CategoryBrandRelation dto2Entity(CategoryBrandRelationDto categoryBrandRelationDto) {
        if (categoryBrandRelationDto == null) {
            return null;
        }
        CategoryBrandRelation  categoryBrandRelation =new  CategoryBrandRelation();
        BeanUtils.copyProperties(categoryBrandRelationDto,  categoryBrandRelation);

        return  categoryBrandRelation;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<CategoryBrandRelationVo> entity2VoList(List<CategoryBrandRelation> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<CategoryBrandRelationVo> listVo = new ArrayList<CategoryBrandRelationVo>();
        for (CategoryBrandRelation item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<CategoryBrandRelation> dto2EntityList(List<CategoryBrandRelationDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<CategoryBrandRelation> listEntity = new ArrayList<CategoryBrandRelation>();
        for (CategoryBrandRelationDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<CategoryBrandRelationVo> entity2VoPage(IPage<CategoryBrandRelation> page) {
        IPage<CategoryBrandRelationVo> vs = page.convert(item -> {
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

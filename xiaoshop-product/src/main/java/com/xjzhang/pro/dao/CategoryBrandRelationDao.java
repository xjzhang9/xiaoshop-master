package com.xjzhang.pro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjzhang.pro.model.entity.CategoryBrandRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌分类关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Mapper
public interface  CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelation> {

}

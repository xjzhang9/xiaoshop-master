package com.xjzhang.pro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.pro.model.dto.BrandDto;
import com.xjzhang.pro.model.entity.Brand;
import com.xjzhang.pro.model.vo.BrandVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Mapper
public interface BrandDao extends BaseMapper<Brand> {
    /**
     * 分页查询商品品牌列表
     *
     * @param brandDto
     * @param queryDtoPage
     * @return
     */
    IPage<BrandVo> queryBrandWithPage(@Param("brandDto") BrandDto brandDto, Page<Brand> queryDtoPage);
}

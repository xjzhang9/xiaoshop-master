package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.BrandDao;
import com.xjzhang.pro.model.dto.BrandDto;
import com.xjzhang.pro.model.entity.Brand;
import com.xjzhang.pro.model.entity.CategoryBrandRelation;
import com.xjzhang.pro.model.vo.BrandVo;
import com.xjzhang.pro.service.BrandService;
import com.xjzhang.pro.service.CategoryBrandRelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 品牌
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("BrandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, Brand> implements BrandService {
    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryBrandRelationService relationService;

    @Override
    public IPage<BrandVo> queryBrandWithPage(BrandDto brandDto, Page<Brand> queryDtoPage) {
        IPage<BrandVo> brandVoIPage = brandDao.queryBrandWithPage(brandDto, queryDtoPage);
        return brandVoIPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateBrandById(Brand brand) {
        this.updateById(brand);
        if (StringUtils.isNotBlank(brand.getName())) {
            relationService.updateBrand(brand.getBrandId(), brand.getName());
        }
        return true;
    }
}

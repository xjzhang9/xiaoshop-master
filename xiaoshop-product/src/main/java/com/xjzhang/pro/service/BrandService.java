package com.xjzhang.pro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.dto.BrandDto;
import com.xjzhang.pro.model.entity.Brand;
import com.xjzhang.pro.model.vo.BrandVo;

/**
 * 品牌
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
public interface BrandService extends IService<Brand> {
    IPage<BrandVo> queryBrandWithPage(BrandDto brandDto);

    boolean updateBrandById(Brand brand);
}

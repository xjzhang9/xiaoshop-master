package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.pro.model.dto.EditBrandDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

import com.xjzhang.pro.model.dto.BrandDto;
import com.xjzhang.pro.model.vo.BrandVo;
import com.xjzhang.pro.model.entity.Brand;

/**
 * 品牌 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class BrandConvert {

    /**
     * entity -> vo
     *
     * @return
     */
    public static BrandVo entity2Vo(Brand brand) {
        if (brand == null) {
            return null;
        }
        BrandVo brandVo = new BrandVo();
        BeanUtils.copyProperties(brand, brandVo);

        return brandVo;
    }

    /**
     * dto -> entity
     *
     * @return
     */
    public static Brand dto2Entity(BrandDto brandDto) {
        if (brandDto == null) {
            return null;
        }
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDto, brand);

        return brand;
    }

    public static Brand editDto2Entity(EditBrandDto brandDto) {
        if (brandDto == null) {
            return null;
        }
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDto, brand);

        return brand;
    }


    /**
     * entityList -> VoList
     *
     * @return
     */
    public static List<BrandVo> entity2VoList(List<Brand> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<BrandVo> listVo = new ArrayList<BrandVo>();
        for (Brand item :
                list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
     * dtoList -> EntityList
     *
     * @return
     */
    public static List<Brand> dto2EntityList(List<BrandDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<Brand> listEntity = new ArrayList<Brand>();
        for (BrandDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
     * entityPage -> VoPage
     *
     * @return
     */
    public static IPage<BrandVo> entity2VoPage(IPage<Brand> page) {
        IPage<BrandVo> vs = page.convert(item -> {
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

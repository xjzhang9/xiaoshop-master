package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;
import com.xjzhang.pro.model.dto.SkuImagesDto;
import com.xjzhang.pro.model.vo.SkuImagesVo;
import com.xjzhang.pro.model.entity.SkuImages;

/**
 * sku图片 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class SkuImagesConvert {

    /**
    * entity -> vo
    * @return
    */
    public static  SkuImagesVo entity2Vo(SkuImages skuImages) {
        if (skuImages ==null){
            return null;
        }
            SkuImagesVo  skuImagesVo = new  SkuImagesVo();
        BeanUtils.copyProperties(skuImages, skuImagesVo);

        return skuImagesVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static SkuImages dto2Entity(SkuImagesDto skuImagesDto) {
        if (skuImagesDto == null) {
            return null;
        }
        SkuImages  skuImages =new  SkuImages();
        BeanUtils.copyProperties(skuImagesDto,  skuImages);

        return  skuImages;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<SkuImagesVo> entity2VoList(List<SkuImages> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SkuImagesVo> listVo = new ArrayList<SkuImagesVo>();
        for (SkuImages item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<SkuImages> dto2EntityList(List<SkuImagesDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SkuImages> listEntity = new ArrayList<SkuImages>();
        for (SkuImagesDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<SkuImagesVo> entity2VoPage(IPage<SkuImages> page) {
        IPage<SkuImagesVo> vs = page.convert(item -> {
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

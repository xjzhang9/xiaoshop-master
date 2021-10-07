package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.pro.model.entity.SpuInfo;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;
import com.xjzhang.pro.model.dto.SpuImagesDto;
import com.xjzhang.pro.model.entity.SpuImages;

/**
 * spu图片 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class SpuImagesConvert {

    /**
    * entity -> vo
    * @return
    */
    public static SpuInfo.SpuImagesVo entity2Vo(SpuImages spuImages) {
        if (spuImages ==null){
            return null;
        }
            SpuInfo.SpuImagesVo spuImagesVo = new SpuInfo.SpuImagesVo();
        BeanUtils.copyProperties(spuImages, spuImagesVo);

        return spuImagesVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static SpuImages dto2Entity(SpuImagesDto spuImagesDto) {
        if (spuImagesDto == null) {
            return null;
        }
        SpuImages  spuImages =new  SpuImages();
        BeanUtils.copyProperties(spuImagesDto,  spuImages);

        return  spuImages;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<SpuInfo.SpuImagesVo> entity2VoList(List<SpuImages> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SpuInfo.SpuImagesVo> listVo = new ArrayList<SpuInfo.SpuImagesVo>();
        for (SpuImages item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<SpuImages> dto2EntityList(List<SpuImagesDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SpuImages> listEntity = new ArrayList<SpuImages>();
        for (SpuImagesDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<SpuInfo.SpuImagesVo> entity2VoPage(IPage<SpuImages> page) {
        IPage<SpuInfo.SpuImagesVo> vs = page.convert(item -> {
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

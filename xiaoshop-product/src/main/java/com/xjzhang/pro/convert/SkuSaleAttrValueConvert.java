package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;
import com.xjzhang.pro.model.dto.SkuSaleAttrValueDto;
import com.xjzhang.pro.model.vo.SkuSaleAttrValueVo;
import com.xjzhang.pro.model.entity.SkuSaleAttrValue;

/**
 * sku销售属性&值 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class SkuSaleAttrValueConvert {

    /**
    * entity -> vo
    * @return
    */
    public static  SkuSaleAttrValueVo entity2Vo(SkuSaleAttrValue skuSaleAttrValue) {
        if (skuSaleAttrValue ==null){
            return null;
        }
            SkuSaleAttrValueVo  skuSaleAttrValueVo = new  SkuSaleAttrValueVo();
        BeanUtils.copyProperties(skuSaleAttrValue, skuSaleAttrValueVo);

        return skuSaleAttrValueVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static SkuSaleAttrValue dto2Entity(SkuSaleAttrValueDto skuSaleAttrValueDto) {
        if (skuSaleAttrValueDto == null) {
            return null;
        }
        SkuSaleAttrValue  skuSaleAttrValue =new  SkuSaleAttrValue();
        BeanUtils.copyProperties(skuSaleAttrValueDto,  skuSaleAttrValue);

        return  skuSaleAttrValue;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<SkuSaleAttrValueVo> entity2VoList(List<SkuSaleAttrValue> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SkuSaleAttrValueVo> listVo = new ArrayList<SkuSaleAttrValueVo>();
        for (SkuSaleAttrValue item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<SkuSaleAttrValue> dto2EntityList(List<SkuSaleAttrValueDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SkuSaleAttrValue> listEntity = new ArrayList<SkuSaleAttrValue>();
        for (SkuSaleAttrValueDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<SkuSaleAttrValueVo> entity2VoPage(IPage<SkuSaleAttrValue> page) {
        IPage<SkuSaleAttrValueVo> vs = page.convert(item -> {
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

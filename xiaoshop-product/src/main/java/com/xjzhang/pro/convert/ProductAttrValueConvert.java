package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.pro.model.dto.ProductAttrValueDto;
import com.xjzhang.pro.model.entity.ProductAttrValue;
import com.xjzhang.pro.model.vo.ProductAttrValueVo;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;

/**
 * spu属性值 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class ProductAttrValueConvert {

    /**
    * entity -> vo
    * @return
    */
    public static ProductAttrValueVo entity2Vo(ProductAttrValue productAttrValue) {
        if (productAttrValue ==null){
            return null;
        }
            ProductAttrValueVo  productAttrValueVo = new  ProductAttrValueVo();
        BeanUtils.copyProperties(productAttrValue, productAttrValueVo);

        return productAttrValueVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static ProductAttrValue dto2Entity(ProductAttrValueDto productAttrValueDto) {
        if (productAttrValueDto == null) {
            return null;
        }
        ProductAttrValue  productAttrValue =new  ProductAttrValue();
        BeanUtils.copyProperties(productAttrValueDto,  productAttrValue);

        return  productAttrValue;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<ProductAttrValueVo> entity2VoList(List<ProductAttrValue> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<ProductAttrValueVo> listVo = new ArrayList<ProductAttrValueVo>();
        for (ProductAttrValue item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<ProductAttrValue> dto2EntityList(List<ProductAttrValueDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<ProductAttrValue> listEntity = new ArrayList<ProductAttrValue>();
        for (ProductAttrValueDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<ProductAttrValueVo> entity2VoPage(IPage<ProductAttrValue> page) {
        IPage<ProductAttrValueVo> vs = page.convert(item -> {
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

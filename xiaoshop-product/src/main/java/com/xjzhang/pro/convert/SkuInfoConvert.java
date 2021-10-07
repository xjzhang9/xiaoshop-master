package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;
import com.xjzhang.pro.model.dto.SkuInfoDto;
import com.xjzhang.pro.model.vo.SkuInfoVo;
import com.xjzhang.pro.model.entity.SkuInfo;

/**
 * sku信息 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class SkuInfoConvert {

    /**
    * entity -> vo
    * @return
    */
    public static  SkuInfoVo entity2Vo(SkuInfo skuInfo) {
        if (skuInfo ==null){
            return null;
        }
            SkuInfoVo  skuInfoVo = new  SkuInfoVo();
        BeanUtils.copyProperties(skuInfo, skuInfoVo);

        return skuInfoVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static SkuInfo dto2Entity(SkuInfoDto skuInfoDto) {
        if (skuInfoDto == null) {
            return null;
        }
        SkuInfo  skuInfo =new  SkuInfo();
        BeanUtils.copyProperties(skuInfoDto,  skuInfo);

        return  skuInfo;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<SkuInfoVo> entity2VoList(List<SkuInfo> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SkuInfoVo> listVo = new ArrayList<SkuInfoVo>();
        for (SkuInfo item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<SkuInfo> dto2EntityList(List<SkuInfoDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SkuInfo> listEntity = new ArrayList<SkuInfo>();
        for (SkuInfoDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<SkuInfoVo> entity2VoPage(IPage<SkuInfo> page) {
        IPage<SkuInfoVo> vs = page.convert(item -> {
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

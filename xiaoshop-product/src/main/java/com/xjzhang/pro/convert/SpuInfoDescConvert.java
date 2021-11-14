package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.pro.model.dto.SpuInfoDescDto;
import com.xjzhang.pro.model.entity.SpuInfoDesc;
import com.xjzhang.pro.model.vo.SpuInfoDescVo;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;


/**
 * spu信息介绍 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class SpuInfoDescConvert {

    /**
    * entity -> vo
    * @return
    */
    public static SpuInfoDescVo entity2Vo(SpuInfoDesc spuInfoDesc) {
        if (spuInfoDesc ==null){
            return null;
        }
            SpuInfoDescVo  spuInfoDescVo = new  SpuInfoDescVo();
        BeanUtils.copyProperties(spuInfoDesc, spuInfoDescVo);

        return spuInfoDescVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static SpuInfoDesc dto2Entity(SpuInfoDescDto spuInfoDescDto) {
        if (spuInfoDescDto == null) {
            return null;
        }
        SpuInfoDesc  spuInfoDesc =new  SpuInfoDesc();
        BeanUtils.copyProperties(spuInfoDescDto,  spuInfoDesc);

        return  spuInfoDesc;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<SpuInfoDescVo> entity2VoList(List<SpuInfoDesc> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SpuInfoDescVo> listVo = new ArrayList<SpuInfoDescVo>();
        for (SpuInfoDesc item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<SpuInfoDesc> dto2EntityList(List<SpuInfoDescDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SpuInfoDesc> listEntity = new ArrayList<SpuInfoDesc>();
        for (SpuInfoDescDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<SpuInfoDescVo> entity2VoPage(IPage<SpuInfoDesc> page) {
        IPage<SpuInfoDescVo> vs = page.convert(item -> {
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

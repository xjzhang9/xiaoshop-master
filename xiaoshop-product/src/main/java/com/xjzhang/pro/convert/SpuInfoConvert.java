package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.pro.model.dto.SpuInfoDto;
import com.xjzhang.pro.model.entity.SpuInfo;
import com.xjzhang.pro.model.vo.SpuInfoVo;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;

/**
 * spu信息 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class SpuInfoConvert {

    /**
    * entity -> vo
    * @return
    */
    public static SpuInfoVo entity2Vo(SpuInfo spuInfo) {
        if (spuInfo ==null){
            return null;
        }
            SpuInfoVo  spuInfoVo = new  SpuInfoVo();
        BeanUtils.copyProperties(spuInfo, spuInfoVo);

        return spuInfoVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static SpuInfo dto2Entity(SpuInfoDto spuInfoDto) {
        if (spuInfoDto == null) {
            return null;
        }
        SpuInfo  spuInfo =new  SpuInfo();
        BeanUtils.copyProperties(spuInfoDto,  spuInfo);

        return  spuInfo;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<SpuInfoVo> entity2VoList(List<SpuInfo> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SpuInfoVo> listVo = new ArrayList<SpuInfoVo>();
        for (SpuInfo item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<SpuInfo> dto2EntityList(List<SpuInfoDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SpuInfo> listEntity = new ArrayList<SpuInfo>();
        for (SpuInfoDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<SpuInfoVo> entity2VoPage(IPage<SpuInfo> page) {
        IPage<SpuInfoVo> vs = page.convert(item -> {
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

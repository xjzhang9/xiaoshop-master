package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.pro.model.dto.AttrAttrgroupRelationDto;
import com.xjzhang.pro.model.entity.AttrAttrgroupRelation;
import com.xjzhang.pro.model.vo.AttrAttrgroupRelationVo;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;

/**
 * 属性&属性分组关联 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class AttrAttrgroupRelationConvert {

    /**
    * entity -> vo
    * @return
    */
    public static AttrAttrgroupRelationVo entity2Vo(AttrAttrgroupRelation attrAttrgroupRelation) {
        if (attrAttrgroupRelation ==null){
            return null;
        }
            AttrAttrgroupRelationVo  attrAttrgroupRelationVo = new  AttrAttrgroupRelationVo();
        BeanUtils.copyProperties(attrAttrgroupRelation, attrAttrgroupRelationVo);

        return attrAttrgroupRelationVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static AttrAttrgroupRelation dto2Entity(AttrAttrgroupRelationDto attrAttrgroupRelationDto) {
        if (attrAttrgroupRelationDto == null) {
            return null;
        }
        AttrAttrgroupRelation  attrAttrgroupRelation =new  AttrAttrgroupRelation();
        BeanUtils.copyProperties(attrAttrgroupRelationDto,  attrAttrgroupRelation);

        return  attrAttrgroupRelation;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<AttrAttrgroupRelationVo> entity2VoList(List<AttrAttrgroupRelation> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<AttrAttrgroupRelationVo> listVo = new ArrayList<AttrAttrgroupRelationVo>();
        for (AttrAttrgroupRelation item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<AttrAttrgroupRelation> dto2EntityList(List<AttrAttrgroupRelationDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<AttrAttrgroupRelation> listEntity = new ArrayList<AttrAttrgroupRelation>();
        for (AttrAttrgroupRelationDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<AttrAttrgroupRelationVo> entity2VoPage(IPage<AttrAttrgroupRelation> page) {
        IPage<AttrAttrgroupRelationVo> vs = page.convert(item -> {
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

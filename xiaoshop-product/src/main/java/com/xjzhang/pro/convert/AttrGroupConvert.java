package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;
import com.xjzhang.pro.model.dto.AttrGroupDto;
import com.xjzhang.pro.model.vo.AttrGroupVo;
import com.xjzhang.pro.model.entity.AttrGroup;

/**
 * 属性分组 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class AttrGroupConvert {

    /**
    * entity -> vo
    * @return
    */
    public static  AttrGroupVo entity2Vo(AttrGroup attrGroup) {
        if (attrGroup ==null){
            return null;
        }
            AttrGroupVo  attrGroupVo = new  AttrGroupVo();
        BeanUtils.copyProperties(attrGroup, attrGroupVo);

        return attrGroupVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static AttrGroup dto2Entity(AttrGroupDto attrGroupDto) {
        if (attrGroupDto == null) {
            return null;
        }
        AttrGroup  attrGroup =new  AttrGroup();
        BeanUtils.copyProperties(attrGroupDto,  attrGroup);

        return  attrGroup;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<AttrGroupVo> entity2VoList(List<AttrGroup> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<AttrGroupVo> listVo = new ArrayList<AttrGroupVo>();
        for (AttrGroup item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<AttrGroup> dto2EntityList(List<AttrGroupDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<AttrGroup> listEntity = new ArrayList<AttrGroup>();
        for (AttrGroupDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<AttrGroupVo> entity2VoPage(IPage<AttrGroup> page) {
        IPage<AttrGroupVo> vs = page.convert(item -> {
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

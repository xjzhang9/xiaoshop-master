package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;
import com.xjzhang.pro.model.dto.AttrDto;
import com.xjzhang.pro.model.vo.AttrVo;
import com.xjzhang.pro.model.entity.Attr;

/**
 * 商品属性 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:34
 */

public class AttrConvert {

    /**
    * entity -> vo
    * @return
    */
    public static  AttrVo entity2Vo(Attr attr) {
        if (attr ==null){
            return null;
        }
            AttrVo  attrVo = new  AttrVo();
        BeanUtils.copyProperties(attr, attrVo);

        return attrVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static Attr dto2Entity(AttrDto attrDto) {
        if (attrDto == null) {
            return null;
        }
        Attr  attr =new  Attr();
        BeanUtils.copyProperties(attrDto,  attr);

        return  attr;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<AttrVo> entity2VoList(List<Attr> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<AttrVo> listVo = new ArrayList<AttrVo>();
        for (Attr item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<Attr> dto2EntityList(List<AttrDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<Attr> listEntity = new ArrayList<Attr>();
        for (AttrDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<AttrVo> entity2VoPage(IPage<Attr> page) {
        IPage<AttrVo> vs = page.convert(item -> {
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

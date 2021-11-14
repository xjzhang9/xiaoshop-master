package com.xjzhang.pro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.dto.AttrAttrgroupRelationDto;
import com.xjzhang.pro.model.dto.AttrDto;
import com.xjzhang.pro.model.dto.NoRelationAttrDto;
import com.xjzhang.pro.model.entity.Attr;
import com.xjzhang.pro.model.vo.AttrVo;
import java.util.List;

/**
 * 商品属性
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:34
 */
public interface AttrService extends IService<Attr> {
    IPage<AttrVo> getNoRelationAttr(NoRelationAttrDto noRelationAttrDto, Long attrGroupId);

    List<AttrVo> attrRelation(Long attrgroupId);

    boolean deleteAttrGroupAttrRelation(Long attrGroupId, Long[] id);

    boolean addAttrRelation(List<AttrAttrgroupRelationDto> attrAttrgroupRelationDtos);

    IPage<AttrVo> getAttrByCatId(int type, Long catId, AttrDto attrDto);
}

package com.xjzhang.pro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.dto.AttrGroupDto;
import com.xjzhang.pro.model.entity.AttrGroup;
import com.xjzhang.pro.model.vo.AttrGroupVo;
import com.xjzhang.pro.model.vo.AttrGroupWithAttrsVo;
import java.util.List;

/**
 * 属性分组
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
public interface AttrGroupService extends IService<AttrGroup> {
    IPage<AttrGroupVo> queryAttrGroupWithPage(IPage<AttrGroupVo> queryDtoPage, AttrGroupDto attrGroupDto);

    boolean deleteAttrGroupAttrRelation(Long id);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);
}

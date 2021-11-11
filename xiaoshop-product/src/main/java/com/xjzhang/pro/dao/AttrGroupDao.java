package com.xjzhang.pro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjzhang.pro.model.dto.AttrGroupDto;
import com.xjzhang.pro.model.entity.AttrGroup;
import com.xjzhang.pro.model.vo.AttrGroupVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 属性分组
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-11-06 11:08:06
 */

@Mapper
public interface  AttrGroupDao extends BaseMapper<AttrGroup> {

    /**
     * 分页获得属性分组列表
     * @param attrGroupDto
     * @param queryDtoPage
     * @return
     */
    IPage<AttrGroupVo> queryAttrGroupWithPage(@Param("attrGroupDto") AttrGroupDto attrGroupDto, Page<AttrGroup> queryDtoPage);
}

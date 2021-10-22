package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.AttrGroupDao;
import com.xjzhang.pro.model.dto.AttrGroupDto;
import com.xjzhang.pro.model.entity.AttrGroup;
import com.xjzhang.pro.model.vo.AttrGroupVo;
import com.xjzhang.pro.service.AttrGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 属性分组
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("AttrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroup> implements AttrGroupService {
   @Autowired
    private  AttrGroupDao attrGroupDao;

    @Override
    public IPage<AttrGroupVo> queryAttrGroupWithPage(Page<AttrGroup> queryDtoPage, AttrGroupDto attrGroupDto) {
        return null;
    }
}

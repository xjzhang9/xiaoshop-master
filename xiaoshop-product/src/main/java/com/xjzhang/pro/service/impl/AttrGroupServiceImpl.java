package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.AttrGroupDao;
import com.xjzhang.pro.model.dto.AttrGroupDto;
import com.xjzhang.pro.model.entity.AttrGroup;
import com.xjzhang.pro.model.vo.AttrGroupVo;
import com.xjzhang.pro.model.vo.AttrGroupWithAttrsVo;
import com.xjzhang.pro.model.vo.AttrVo;
import com.xjzhang.pro.service.AttrAttrgroupRelationService;
import com.xjzhang.pro.service.AttrGroupService;
import com.xjzhang.pro.service.AttrService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 属性分组
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("AttrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroup> implements AttrGroupService {
    @Resource
    private AttrGroupDao attrGroupDao;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Override
    public IPage<AttrGroupVo> queryAttrGroupWithPage(IPage<AttrGroupVo> queryDtoPage, AttrGroupDto attrGroupDto) {
        IPage<AttrGroupVo> attrGroupVoIPage = attrGroupDao.queryAttrGroupWithPage(attrGroupDto, queryDtoPage);
        return attrGroupVoIPage;
    }

    @Override
    public boolean deleteAttrGroupAttrRelation(Long id) {

        return false;
    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        List<AttrGroup> list = this.list(new LambdaQueryWrapper<AttrGroup>().eq(AttrGroup::getCatelogId, catelogId));
        List<AttrGroupWithAttrsVo> collect = null;
        if (list != null && list.size() > 0) {
            collect = list.stream().map(
                    attrGroup -> {
                        AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
                        BeanUtils.copyProperties(attrGroup, attrGroupWithAttrsVo);
                        List<AttrVo> attrList = attrService.attrRelation(attrGroup.getAttrGroupId());
                        attrGroupWithAttrsVo.setAttrs(attrList);
                        return attrGroupWithAttrsVo;
                    }
            ).collect(Collectors.toList());
        }
        return collect;
    }
}

package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.base.model.QueryDto;
import com.xjzhang.pro.constant.ProductConstant;
import com.xjzhang.pro.convert.AttrAttrgroupRelationConvert;
import com.xjzhang.pro.convert.AttrConvert;
import com.xjzhang.pro.dao.AttrDao;
import com.xjzhang.pro.model.dto.AttrAttrgroupRelationDto;
import com.xjzhang.pro.model.dto.AttrDto;
import com.xjzhang.pro.model.dto.NoRelationAttrDto;
import com.xjzhang.pro.model.entity.Attr;
import com.xjzhang.pro.model.entity.AttrAttrgroupRelation;
import com.xjzhang.pro.model.entity.AttrGroup;
import com.xjzhang.pro.model.entity.Brand;
import com.xjzhang.pro.model.vo.AttrGroupVo;
import com.xjzhang.pro.model.vo.AttrVo;
import com.xjzhang.pro.service.AttrAttrgroupRelationService;
import com.xjzhang.pro.service.AttrGroupService;
import com.xjzhang.pro.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.schema.Collections;
import sun.security.krb5.internal.PAData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品属性
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:34
 */

@Service("AttrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, Attr> implements AttrService {
    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Override
    public IPage<AttrVo> getNoRelationAttr(NoRelationAttrDto noRelationAttrDto, Long attrGroupId) {
        // 1、获得分组分类
        AttrGroup attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        // 2、获得分类下的所有分组
        List<AttrGroup> attrGroupList = attrGroupService.list(new LambdaQueryWrapper<AttrGroup>().eq(AttrGroup::getCatelogId, catelogId));

        IPage<AttrVo> voIPage = new Page<AttrVo>();
        if (attrGroupList != null && attrGroupList.size() > 0) {
            List<Long> attrGroupIdList = attrGroupList.stream().map(attrGroup1 -> {
                return attrGroup1.getAttrGroupId();
            }).collect(Collectors.toList());
            // 3、获得分类下的所有分组的属性
            List<AttrAttrgroupRelation> attrAttrgroupRelationList = attrAttrgroupRelationService.list(new LambdaQueryWrapper<AttrAttrgroupRelation>().in(AttrAttrgroupRelation::getAttrGroupId, attrGroupIdList));

            List<Long> attrIdList = null;
            if (attrAttrgroupRelationList != null || attrAttrgroupRelationList.size() > 0) {
                attrIdList = attrAttrgroupRelationList.stream()
                        .map(attrAttrgroupRelation -> {
                            return attrAttrgroupRelation.getAttrId();
                        }).collect(Collectors.toList());
            }

            // 4、剔除掉分类下已经有分组的属性
            LambdaQueryWrapper<Attr> lambdaQueryWrapper =  new LambdaQueryWrapper<Attr>()
                    .eq(Attr::getCatelogId, catelogId).eq(Attr::getAttrType, ProductConstant.AttrEnum.ATTR_TYPE_BASE);
            if (attrIdList != null && attrIdList.size() > 0) {
                lambdaQueryWrapper.notIn(Attr::getAttrId, attrIdList);
            }

            if (StringUtils.isNotBlank(noRelationAttrDto.getAttrName())) {
                lambdaQueryWrapper.like(Attr::getAttrName, noRelationAttrDto.getAttrName());
            }

            Page<Attr> queryDtoPage = new Page(noRelationAttrDto.getPageIndex(), noRelationAttrDto.getPageSize());
            IPage<Attr> page = this.page(queryDtoPage, lambdaQueryWrapper);
            voIPage = AttrConvert.entity2VoPage(page);
        }


        return voIPage;
    }

    @Override
    public List<AttrVo> attrRelation(Long attrgroupId) {
        LambdaQueryWrapper<AttrAttrgroupRelation> lambdaQueryWrapper = new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrGroupId, attrgroupId);
        List<AttrAttrgroupRelation> attrAttrgroupRelationList = this.attrAttrgroupRelationService.list(lambdaQueryWrapper);
        List<Long> attrIdList   = attrAttrgroupRelationList.stream().map(attrAttrgroupRelation -> {
            return attrAttrgroupRelation.getAttrId();
        }).collect(Collectors.toList());

        LambdaQueryWrapper<Attr> attrQuery = new LambdaQueryWrapper<Attr>().in(Attr::getAttrId, attrIdList);

        List<Attr> attrList =   this.list(attrQuery);
        return AttrConvert.entity2VoList(attrList);
    }

    @Override
    public boolean deleteAttrGroupAttrRelation(Long attrGroupId, Long[] ids) {
        return attrAttrgroupRelationService.remove(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrGroupId, attrGroupId).in(AttrAttrgroupRelation::getAttrId, ids));
    }

    @Override
    public boolean addAttrRelation(List<AttrAttrgroupRelationDto> attrAttrgroupRelationDtos) {
        return attrAttrgroupRelationService.saveBatch( AttrAttrgroupRelationConvert.dto2EntityList(attrAttrgroupRelationDtos), attrAttrgroupRelationDtos.size());
    }

    @Override
    public IPage<AttrVo> getAttrByCatId(String type, Long catId, AttrDto attrDto) {
        Page<Attr> queryDtoPage = new Page(attrDto.getPageIndex(), attrDto.getPageSize());
        IPage<Attr> page = this.page(queryDtoPage, new LambdaQueryWrapper<Attr>().eq(Attr::getCatelogId, catId).eq(Attr::getAttrType, type).like(Attr::getAttrName, attrDto.getAttrName()));

        return AttrConvert.entity2VoPage(page);
    }
}

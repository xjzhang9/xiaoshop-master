package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.AttrAttrgroupRelationDao;
import com.xjzhang.pro.model.entity.AttrAttrgroupRelation;
import com.xjzhang.pro.service.AttrAttrgroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 属性&属性分组关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("AttrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelation> implements AttrAttrgroupRelationService {
   @Resource
    private  AttrAttrgroupRelationDao attrAttrgroupRelationDao;
}

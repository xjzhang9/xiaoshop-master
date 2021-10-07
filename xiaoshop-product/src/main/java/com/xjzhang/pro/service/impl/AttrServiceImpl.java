package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.AttrDao;
import com.xjzhang.pro.model.entity.Attr;
import com.xjzhang.pro.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

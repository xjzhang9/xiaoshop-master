package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.ProductAttrValueDao;
import com.xjzhang.pro.model.entity.ProductAttrValue;
import com.xjzhang.pro.service.ProductAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * spu属性值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("ProductAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValue> implements ProductAttrValueService {
   @Autowired
    private  ProductAttrValueDao productAttrValueDao;
}

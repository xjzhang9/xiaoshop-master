package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.SkuSaleAttrValueDao;
import com.xjzhang.pro.model.entity.SkuSaleAttrValue;
import com.xjzhang.pro.model.vo.SkuItemSaleAttrVo;
import com.xjzhang.pro.service.SkuSaleAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * sku销售属性&值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("SkuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValue> implements SkuSaleAttrValueService {
   @Autowired
    private  SkuSaleAttrValueDao skuSaleAttrValueDao;

    @Override
    public List<SkuItemSaleAttrVo> listSaleAttr(Long skuId) {
        return skuSaleAttrValueDao.listSaleAttr(skuId);
    }
}

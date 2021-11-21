package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.convert.ProductAttrValueConvert;
import com.xjzhang.pro.dao.ProductAttrValueDao;
import com.xjzhang.pro.model.entity.ProductAttrValue;
import com.xjzhang.pro.model.vo.ProductAttrValueVo;
import com.xjzhang.pro.model.vo.SpuItemAttrGroupVo;
import com.xjzhang.pro.service.ProductAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * spu属性值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("ProductAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValue> implements ProductAttrValueService {
   @Resource
    private  ProductAttrValueDao productAttrValueDao;

    @Override
    public List<ProductAttrValueVo> baseAttrlistforspu(Long spuId) {
        List<ProductAttrValue> productAttrValueList =  this.list(new LambdaQueryWrapper<ProductAttrValue>().eq(ProductAttrValue::getSpuId, spuId));
        return ProductAttrValueConvert.entity2VoList(productAttrValueList);
    }

    @Override
    public List<SpuItemAttrGroupVo> getProductGroupAttrsBySpuId(Long spuId, Long catalogId) {
        return productAttrValueDao.getProductGroupAttrsBySpuId(spuId, catalogId);
    }
}

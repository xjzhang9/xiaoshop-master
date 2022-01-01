package com.xjzhang.pro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjzhang.pro.model.entity.SkuSaleAttrValue;
import com.xjzhang.pro.model.vo.SkuItemSaleAttrVo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * sku销售属性&值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Mapper
public interface  SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValue> {
    List<SkuItemSaleAttrVo> listSaleAttr(Long skuId);

    List<String> getSkuSaleAttrValuesAsString(Long skuId);
}

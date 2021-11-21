package com.xjzhang.pro.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.entity.SkuSaleAttrValue;
import com.xjzhang.pro.model.vo.SkuItemSaleAttrVo;

import java.util.List;

/**
 * sku销售属性&值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValue> {
    List<SkuItemSaleAttrVo> listSaleAttr(Long skuId);
}

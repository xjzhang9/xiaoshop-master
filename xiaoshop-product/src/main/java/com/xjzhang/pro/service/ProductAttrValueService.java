package com.xjzhang.pro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.entity.ProductAttrValue;
import com.xjzhang.pro.model.vo.ProductAttrValueVo;
import com.xjzhang.pro.model.vo.SpuItemAttrGroupVo;

import java.util.List;

/**
 * spu属性值
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
public interface ProductAttrValueService extends IService<ProductAttrValue> {
    List<ProductAttrValueVo> baseAttrlistforspu(Long spuId);

    List<SpuItemAttrGroupVo> getProductGroupAttrsBySpuId(Long spuId, Long catalogId);
}

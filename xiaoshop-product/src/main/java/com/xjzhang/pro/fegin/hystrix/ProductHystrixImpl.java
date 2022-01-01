package com.xjzhang.pro.fegin.hystrix;

import com.xjzhang.base.model.es.SkuEsModel;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.es.fegin.api.EsSaveFeginApi;
import com.xjzhang.pro.fegin.api.ProductFeginApi;
import com.xjzhang.pro.model.vo.SkuInfoVo;
import org.springframework.stereotype.Component;

import java.util.List;

/** essave fegin hystrix熔断返回
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/13 17:12
 */
@Component
public class ProductHystrixImpl implements ProductFeginApi {
    @Override
    public BaseWrapper<SkuInfoVo> getSkuInfoById(Long id) {
        return null;
    }

    @Override
    public BaseWrapper<List<String>> getSkuSaleAttrValuesAsString(Long skuId) {
        return null;
    }
}

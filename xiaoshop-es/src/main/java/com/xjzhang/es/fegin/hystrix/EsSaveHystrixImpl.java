package com.xjzhang.es.fegin.hystrix;

import com.xjzhang.base.model.es.SkuEsModel;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.es.fegin.api.EsSaveFeginApi;
import org.springframework.stereotype.Component;

import java.util.List;

/** essave fegin hystrix熔断返回
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/13 17:12
 */
@Component
public class EsSaveHystrixImpl implements EsSaveFeginApi {
    @Override
    public BaseWrapper saveProductAsIndices(List<SkuEsModel> skuEsModels) {
        return null;
    }
}

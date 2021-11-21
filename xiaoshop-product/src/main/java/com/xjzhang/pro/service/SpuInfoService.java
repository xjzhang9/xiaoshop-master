package com.xjzhang.pro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.dto.SaveSpuInfoDto;
import com.xjzhang.pro.model.entity.SpuInfo;
import com.xjzhang.pro.model.vo.Catelog2Vo;
import com.xjzhang.pro.model.vo.SkuItemVo;

import java.util.List;
import java.util.Map;

/**
 * spu信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
public interface SpuInfoService extends IService<SpuInfo> {
    boolean saveSpuInfo(SaveSpuInfoDto spuInfo);

    /**
     * 商品上架
     * @param spuId
     * @return
     */
    void upSpuForSearch(Long spuId);

    SkuItemVo getItemInfo(Long skuId);
}

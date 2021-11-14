package com.xjzhang.pro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.dto.SaveSpuInfoDto;
import com.xjzhang.pro.model.entity.SpuInfo;

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
}

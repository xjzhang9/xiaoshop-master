package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.SkuInfoDao;
import com.xjzhang.pro.model.entity.SkuInfo;
import com.xjzhang.pro.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * sku信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("SkuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfo> implements SkuInfoService {
   @Autowired
    private  SkuInfoDao skuInfoDao;
}

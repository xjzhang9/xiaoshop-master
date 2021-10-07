package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.SpuInfoDao;
import com.xjzhang.pro.model.entity.SpuInfo;
import com.xjzhang.pro.service.SpuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * spu信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("SpuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfo> implements SpuInfoService {
   @Autowired
    private  SpuInfoDao spuInfoDao;
}

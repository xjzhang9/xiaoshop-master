package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.SpuInfoDescDao;
import com.xjzhang.pro.model.entity.SpuInfoDesc;
import com.xjzhang.pro.service.SpuInfoDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * spu信息介绍
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("SpuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDesc> implements SpuInfoDescService {
   @Autowired
    private  SpuInfoDescDao spuInfoDescDao;
}

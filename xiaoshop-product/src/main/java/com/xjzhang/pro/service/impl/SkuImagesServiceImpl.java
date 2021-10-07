package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.SkuImagesDao;
import com.xjzhang.pro.model.entity.SkuImages;
import com.xjzhang.pro.service.SkuImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * sku图片
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("SkuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImages> implements SkuImagesService {
   @Autowired
    private SkuImagesDao skuImagesDao;
}

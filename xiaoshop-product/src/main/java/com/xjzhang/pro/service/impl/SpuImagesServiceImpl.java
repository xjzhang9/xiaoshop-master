package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.SpuImagesDao;
import com.xjzhang.pro.model.entity.SpuImages;
import com.xjzhang.pro.service.SpuImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * spu图片
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("SpuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImages> implements SpuImagesService {
   @Autowired
    private SpuImagesDao spuImagesDao;
}

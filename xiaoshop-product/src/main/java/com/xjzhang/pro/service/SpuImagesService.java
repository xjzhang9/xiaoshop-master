package com.xjzhang.pro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjzhang.pro.model.entity.SpuImages;
import java.util.List;

/**
 * spu图片
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SpuImagesService extends IService<SpuImages> {
    void saveImages(Long id, List<String> images);
}


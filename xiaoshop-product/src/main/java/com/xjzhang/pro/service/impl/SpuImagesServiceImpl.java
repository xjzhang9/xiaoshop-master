package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.SpuImagesDao;
import com.xjzhang.pro.model.entity.SpuImages;
import com.xjzhang.pro.service.SpuImagesService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author THTF
 */
@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImages> implements SpuImagesService {

    @Override
    public void saveImages(Long id, List<String> images) {
        if(images == null || images.size() == 0){

        }else{
            List<SpuImages> collect = images.stream().map(img -> {
                SpuImages spuImagesEntity = new SpuImages();
                spuImagesEntity.setSpuId(id);
                spuImagesEntity.setImgUrl(img);

                return spuImagesEntity;
            }).collect(Collectors.toList());

            this.saveBatch(collect);
        }
    }

}

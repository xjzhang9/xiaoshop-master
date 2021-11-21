package com.xjzhang.pro.model.vo;

import com.xjzhang.pro.model.entity.SkuImages;
import com.xjzhang.pro.model.entity.SkuInfo;
import com.xjzhang.pro.model.entity.SpuInfoDesc;
import lombok.Data;

import java.util.List;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/21 10:58
 */
@Data
public class SkuItemVo {
    //1、sku基本信息的获取
    private SkuInfo info;

    private boolean hasStock = true;

    //2、sku的图片信息
    private List<SkuImages> images;

    //3、获取spu的销售属性组合
    private List<SkuItemSaleAttrVo> saleAttr;

    //4、获取spu的介绍
    private SpuInfoDesc desc;

    //5、获取spu的规格参数信息
    private List<SpuItemAttrGroupVo> groupAttrs;

    //6、秒杀商品的优惠信息
    private SeckillSkuVo seckillSkuVo;
}

package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.base.model.es.SkuEsModel;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.es.fegin.api.EsSaveFeginApi;
import com.xjzhang.pro.constant.ProductConstant;
import com.xjzhang.pro.dao.SpuInfoDao;
import com.xjzhang.pro.exception.ProBizException;
import com.xjzhang.pro.model.dto.BaseAttrs;
import com.xjzhang.pro.model.dto.Images;
import com.xjzhang.pro.model.dto.SaveSpuInfoDto;
import com.xjzhang.pro.model.dto.Skus;
import com.xjzhang.pro.model.entity.*;
import com.xjzhang.pro.model.vo.*;
import com.xjzhang.pro.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * spu信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Slf4j
@Service("SpuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfo> implements SpuInfoService {
    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Autowired
    SpuImagesService imagesService;

    @Autowired
    AttrService attrService;

    @Autowired
    ProductAttrValueService attrValueService;

    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    SkuImagesService skuImagesService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Resource
    private EsSaveFeginApi esSaveFeginApi;

    @Resource
    private SpuInfoDao spuInfoDao;

    @Resource
    private TaskExecutor taskExecutor;

    @Override
    public boolean saveSpuInfo(SaveSpuInfoDto spuInfo) {
        //1、保存spu基本信息 pms_spu_info
        SpuInfo infoEntity = new SpuInfo();
        BeanUtils.copyProperties(spuInfo, infoEntity);
        infoEntity.setCreateTime(new Date());
        infoEntity.setUpdateTime(new Date());
        this.save(infoEntity);

        //2、保存Spu的描述图片 pms_spu_info_desc
        List<String> decript = spuInfo.getDecript();
        SpuInfoDesc descEntity = new SpuInfoDesc();
        descEntity.setSpuId(infoEntity.getId());
        descEntity.setDecript(String.join(",", decript));
        spuInfoDescService.save(descEntity);

        //3、保存spu的图片集 pms_spu_images
        List<String> images = spuInfo.getImages();
        imagesService.saveImages(infoEntity.getId(), images);


        //4、保存spu的规格参数;pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuInfo.getBaseAttrs();
        List<ProductAttrValue> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValue valueEntity = new ProductAttrValue();
            valueEntity.setAttrId(attr.getAttrId());
            Attr id = attrService.getById(attr.getAttrId());
            valueEntity.setAttrName(id.getAttrName());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(infoEntity.getId());

            return valueEntity;
        }).collect(Collectors.toList());
        attrValueService.saveBatch(collect);


//        //5、保存spu的积分信息；gulimall_sms->sms_spu_bounds
//        Bounds bounds = spuInfo.getBounds();
//        SpuBoundTo spuBoundTo = new SpuBoundTo();
//        BeanUtils.copyProperties(bounds,spuBoundTo);
//        spuBoundTo.setSpuId(infoEntity.getId());
//        R r = couponFeignService.saveSpuBounds(spuBoundTo);
//        if(r.getCode() != 0){
//            log.error("远程保存spu积分信息失败");
//        }


        //5、保存当前spu对应的所有sku信息；

        List<Skus> skus = spuInfo.getSkus();
        if (skus != null && skus.size() > 0) {
            skus.forEach(item -> {
                String defaultImg = "";
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImg = image.getImgUrl();
                    }
                }
                //    private String skuName;
                //    private BigDecimal price;
                //    private String skuTitle;
                //    private String skuSubtitle;
                SkuInfo skuInfoEntity = new SkuInfo();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(infoEntity.getBrandId());
                skuInfoEntity.setCatalogId(infoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(infoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                //5.1）、sku的基本信息；pms_sku_info
                skuInfoService.save(skuInfoEntity);

                Long skuId = skuInfoEntity.getSkuId();

                List<SkuImages> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImages skuImagesEntity = new SkuImages();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).filter(entity -> {
                    //返回true就是需要，false就是剔除
                    return !StringUtils.isEmpty(entity.getImgUrl());
                }).collect(Collectors.toList());
                //5.2）、sku的图片信息；pms_sku_image
                skuImagesService.saveBatch(imagesEntities);
                //TODO 没有图片路径的无需保存

                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValue> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValue attrValueEntity = new SkuSaleAttrValue();
                    BeanUtils.copyProperties(a, attrValueEntity);
                    attrValueEntity.setSkuId(skuId);

                    return attrValueEntity;
                }).collect(Collectors.toList());
                //5.3）、sku的销售属性信息：pms_sku_sale_attr_value
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

//                // //5.4）、sku的优惠、满减等信息；gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
//                SkuReductionTo skuReductionTo = new SkuReductionTo();
//                BeanUtils.copyProperties(item,skuReductionTo);
//                skuReductionTo.setSkuId(skuId);
//                if(skuReductionTo.getFullCount() >0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1){
//                    R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
//                    if(r1.getCode() != 0){
//                        log.error("远程保存sku优惠信息失败");
//                    }
//                }
            });
        }
        return true;
    }

    @Override
    public void upSpuForSearch(Long spuId) {
        // 1、构建商品结构，es mapper
        // 2、构建商品es数据结构。
        // 3、创建商品es 索引
        List<SkuInfo> skuInfoList = skuInfoService.list(new LambdaQueryWrapper<SkuInfo>().eq(SkuInfo::getSpuId, spuId));

        // 规格参数信息
        List<ProductAttrValueVo> productAttrValueVos = attrValueService.baseAttrlistforspu(spuId);
        List<Long> attrIdList = productAttrValueVos.stream().map(productAttrValueVo -> {
            return productAttrValueVo.getAttrId();
        }).collect(Collectors.toList());

        List<Long> attrIdSearchList = attrService.list(new LambdaQueryWrapper<Attr>().in(Attr::getAttrId, attrIdList).eq(Attr::getSearchType, 1))
                .stream().map(attr -> {
                            return attr.getAttrId();
                        }
                ).collect(Collectors.toList());

        List<SkuEsModel.Attr> attrs = productAttrValueVos.stream().filter(productAttrValueVo -> {
            return attrIdSearchList.contains(productAttrValueVo.getAttrId());
        }).map(productAttrValueVo -> {
            SkuEsModel.Attr attr = new SkuEsModel.Attr();
            BeanUtils.copyProperties(productAttrValueVo, attr);
            return attr;
        }).collect(Collectors.toList());

        // 设置sku的es信息
        List<SkuEsModel> skuEsModelList = skuInfoList.stream().map(skuInfo -> {
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(skuInfo, skuEsModel);

            skuEsModel.setAttrs(attrs);

            // 获取品牌信息
            Brand brand = brandService.getById(skuInfo.getBrandId());
            skuEsModel.setBrandName(brand.getName());
            skuEsModel.setBrandImg(brand.getLogo());

            //获取分类信息
            CategoryVo categoryVo = categoryService.getCategoryById(skuInfo.getCatalogId());
            skuEsModel.setCatalogId(categoryVo.getCatId());
            skuEsModel.setCatalogName(categoryVo.getName());

            // TODO 获得是否有库存信息
            skuEsModel.setHasStock(true);

            // 设置热度得分，默认为0
            skuEsModel.setHotScore(0L);

            return skuEsModel;
        }).collect(Collectors.toList());

        BaseWrapper baseWrapper = esSaveFeginApi.saveProductAsIndices(skuEsModelList);
        if (baseWrapper.success()) {
            spuInfoDao.upSpuStatus(spuId, ProductConstant.ProductStatusEnum.SPU_UP.getCode());
        } else {
            log.error("商品远程上架es保存失败");
        }
    }

    @Override
    public SkuItemVo getItemInfo(Long skuId) {
        SkuItemVo skuItemVo = new SkuItemVo();

        //1、获取sku信息
        CompletableFuture<SkuInfo> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            SkuInfo skuInfo = skuInfoService.getById(skuId);
            skuItemVo.setInfo(skuInfo);
            return skuInfo;
        }, taskExecutor);

        //2、获取sku图片信息
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            List<SkuImages> skuImagesList = skuImagesService.list(new LambdaQueryWrapper<SkuImages>().eq(SkuImages::getSkuId, skuId));
            skuItemVo.setImages(skuImagesList);
        }, taskExecutor);

        //3、获取spu下面的销售属性集合
        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(() -> {
            List<SkuItemSaleAttrVo> skuItemSaleAttrVoList = skuSaleAttrValueService.listSaleAttr(skuId);
            skuItemVo.setSaleAttr(skuItemSaleAttrVoList);
        }, taskExecutor);

        // 4、获取spu的介绍信息,依赖步骤1
        CompletableFuture<Void> completableFuture4 = completableFuture1.thenAcceptAsync(skuInfo -> {
            SpuInfoDesc spuInfoDesc = spuInfoDescService.getById(skuInfo.getSpuId());
            skuItemVo.setDesc(spuInfoDesc);
        }, taskExecutor);

        // 5、获取spu的规格参数信息
        CompletableFuture<Void> completableFuture5 = completableFuture1.thenAcceptAsync(skuInfo -> {
            List<SpuItemAttrGroupVo> spuItemAttrGroupVos = attrValueService.getProductGroupAttrsBySpuId(skuInfo.getSpuId(), skuInfo.getCatalogId());
            skuItemVo.setGroupAttrs(spuItemAttrGroupVos);
        }, taskExecutor);

        //TODO: 6、获取秒杀和优惠券信息

        try {
            CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3, completableFuture4, completableFuture5).get();
        } catch (InterruptedException e) {
            log.error("获取商品详情信息异常, message = %", e.getMessage());
            throw e;
        } catch (ExecutionException e) {
            log.error("获取商品详情信息异常, message = %", e.getMessage());
            throw e;
        } finally {
            return skuItemVo;
        }
    }
}

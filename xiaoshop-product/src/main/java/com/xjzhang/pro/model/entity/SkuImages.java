package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * sku图片
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_sku_images")
public class SkuImages extends BaseEntity {
    /**
    *  默认图[0 - 不是默认图，1 - 是默认图]
    */
        @TableId
        @TableField("default_img")
    private Integer defaultImg;
        /**
    *  id
    */
        @TableField("id")
    private Long id;
        /**
    *  排序
    */
        @TableField("img_sort")
    private Integer imgSort;
        /**
    *  图片地址
    */
        @TableField("img_url")
    private String imgUrl;
        /**
    *  sku_id
    */
        @TableField("sku_id")
    private Long skuId;
    }

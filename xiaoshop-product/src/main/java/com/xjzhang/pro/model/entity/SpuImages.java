package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * spu图片
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_spu_images")
public class SpuImages extends BaseEntity {
    /**
    *  是否默认图
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
    *  图片名
    */
        @TableField("img_name")
    private String imgName;
        /**
    *  顺序
    */
        @TableField("img_sort")
    private Integer imgSort;
        /**
    *  图片地址
    */
        @TableField("img_url")
    private String imgUrl;
        /**
    *  spu_id
    */
        @TableField("spu_id")
    private Long spuId;
    }

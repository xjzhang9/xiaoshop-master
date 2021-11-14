package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * spu信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_spu_info")
public class SpuInfo {
    /**
     * 品牌id
     */
    @TableId
    @TableField("brand_id")
    private Long brandId;
    /**
     * 所属分类id
     */
    @TableField("catalog_id")
    private Long catalogId;
    /**
     *
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 商品id
     */
    @TableField("id")
    private Long id;
    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    @TableField("publish_status")
    private Integer publishStatus;
    /**
     * 商品描述
     */
    @TableField("spu_description")
    private String spuDescription;
    /**
     * 商品名称
     */
    @TableField("spu_name")
    private String spuName;
    /**
     *
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     *
     */
    @TableField("weight")
    private BigDecimal weight;

    /**
     * spu_id
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * spu图片
     *
     * @Author xjzhang
     * @email xjzhang@163.com
     * @date 2021-10-05 17:10:35
     */
    @Data
    @EqualsAndHashCode()
    public static class SpuImagesVo extends BaseVo {
        /**
         * 是否默认图
         */
        private Integer defaultImg;
        /**
         * id
         */
        private Long id;
        /**
         * 图片名
         */
        private String imgName;
        /**
         * 顺序
         */
        private Integer imgSort;
        /**
         * 图片地址
         */
        private String imgUrl;
        /**
         * spu_id
         */
        private Long spuId;
    }
}

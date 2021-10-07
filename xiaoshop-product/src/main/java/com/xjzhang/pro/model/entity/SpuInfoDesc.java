package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * spu信息介绍
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_spu_info_desc")
public class SpuInfoDesc extends BaseEntity {
    /**
    *  商品介绍
    */
        @TableId
        @TableField("decript")
    private String decript;
        /**
    *  商品id
    */
        @TableField("spu_id")
    private Long spuId;
    }

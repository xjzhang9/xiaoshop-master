package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * 品牌
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_brand")
public class Brand {
    /**
    *  品牌id
    */
        @TableId
        @TableField("brand_id")
        private Long brandId;
        /**
    *  介绍
    */
        @TableField("descript")
    private String descript;
        /**
    *  检索首字母
    */
        @TableField("first_letter")
    private String firstLetter;
        /**
    *  品牌logo地址
    */
        @TableField("logo")
    private String logo;
        /**
    *  品牌名
    */
        @TableField("name")
    private String name;
        /**
    *  显示状态[0-不显示；1-显示]
    */
        @TableField("show_status")
    private Integer showStatus;
        /**
    *  排序
    */
        @TableField("sort")
    private Integer sort;
    }

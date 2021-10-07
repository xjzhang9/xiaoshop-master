package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * 属性分组
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_attr_group")
public class AttrGroup extends BaseEntity {
    /**
    *  分组id
    */
        @TableId
        @TableField("attr_group_id")
    private Long attrGroupId;
        /**
    *  组名
    */
        @TableField("attr_group_name")
    private String attrGroupName;
        /**
    *  所属分类id
    */
        @TableField("catelog_id")
    private Long catelogId;
        /**
    *  描述
    */
        @TableField("descript")
    private String descript;
        /**
    *  组图标
    */
        @TableField("icon")
    private String icon;
        /**
    *  排序
    */
        @TableField("sort")
    private Integer sort;
    }

package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * 属性&属性分组关联
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_attr_attrgroup_relation")
public class AttrAttrgroupRelation {
    /**
    *  属性分组id
    */
        @TableField("attr_group_id")
    private Long attrGroupId;
        /**
    *  属性id
    */
        @TableField("attr_id")
    private Long attrId;
        /**
    *  属性组内排序
    */
        @TableField("attr_sort")
    private Integer attrSort;
        /**
    *  id
    */
        @TableField("id")
        @TableId
       private Long id;
    }

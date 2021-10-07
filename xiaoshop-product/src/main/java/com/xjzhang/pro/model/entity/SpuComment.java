package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 商品评价
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_spu_comment")
public class SpuComment extends BaseEntity {
    /**
    *  评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
    */
        @TableId
        @TableField("comment_type")
    private Integer commentType;
        /**
    *  内容
    */
        @TableField("content")
    private String content;
        /**
    *  创建时间
    */
        @TableField("create_time")
    private Date createTime;
        /**
    *  id
    */
        @TableField("id")
    private Long id;
        /**
    *  点赞数
    */
        @TableField("likes_count")
    private Integer likesCount;
        /**
    *  用户头像
    */
        @TableField("member_icon")
    private String memberIcon;
        /**
    *  会员ip
    */
        @TableField("member_ip")
    private String memberIp;
        /**
    *  会员昵称
    */
        @TableField("member_nick_name")
    private String memberNickName;
        /**
    *  回复数
    */
        @TableField("reply_count")
    private Integer replyCount;
        /**
    *  评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
    */
        @TableField("resources")
    private String resources;
        /**
    *  显示状态[0-不显示，1-显示]
    */
        @TableField("show_status")
    private Integer showStatus;
        /**
    *  sku_id
    */
        @TableField("sku_id")
    private Long skuId;
        /**
    *  购买时属性组合
    */
        @TableField("spu_attributes")
    private String spuAttributes;
        /**
    *  spu_id
    */
        @TableField("spu_id")
    private Long spuId;
        /**
    *  商品名字
    */
        @TableField("spu_name")
    private String spuName;
        /**
    *  星级
    */
        @TableField("star")
    private Integer star;
    }

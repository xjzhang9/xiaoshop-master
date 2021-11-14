package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品评价
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "商品评价Dto")
public class SpuCommentDto extends BaseQuery {
    /**
     *  评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
     */
    private Integer commentType;
    /**
     *  内容
     */
    private String content;
    /**
     *  创建时间
     */
    private Date createTime;
    /**
     *  id
     */
    private Long id;
    /**
     *  点赞数
     */
    private Integer likesCount;
    /**
     *  用户头像
     */
    private String memberIcon;
    /**
     *  会员ip
     */
    private String memberIp;
    /**
     *  会员昵称
     */
    private String memberNickName;
    /**
     *  回复数
     */
    private Integer replyCount;
    /**
     *  评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
     */
    private String resources;
    /**
     *  显示状态[0-不显示，1-显示]
     */
    private Integer showStatus;
    /**
     *  sku_id
     */
    private Long skuId;
    /**
     *  购买时属性组合
     */
    private String spuAttributes;
    /**
     *  spu_id
     */
    private Long spuId;
    /**
     *  商品名字
     */
    private String spuName;
    /**
     *  星级
     */
    private Integer star;
}

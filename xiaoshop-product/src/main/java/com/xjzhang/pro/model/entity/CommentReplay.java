package com.xjzhang.pro.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xjzhang.base.model.BaseEntity;
import lombok.Data;

/**
 * 商品评价回复关系
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Data
@TableName("tb_pro_comment_replay")
public class CommentReplay extends BaseEntity {
    /**
    *  评论id
    */
        @TableId
        @TableField("comment_id")
    private Long commentId;
        /**
    *  id
    */
        @TableField("id")
    private Long id;
        /**
    *  回复id
    */
        @TableField("reply_id")
    private Long replyId;
    }

package com.xjzhang.pro.model.vo;

import com.xjzhang.base.model.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品评价回复关系
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@Data
@EqualsAndHashCode()
public class CommentReplayVo extends BaseVo {
    /**
    *  评论id
    */
    private Long commentId;
    /**
    *  id
    */
    private Long id;
    /**
    *  回复id
    */
    private Long replyId;
}

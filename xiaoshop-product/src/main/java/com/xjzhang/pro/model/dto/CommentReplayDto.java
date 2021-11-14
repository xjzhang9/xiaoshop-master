package com.xjzhang.pro.model.dto;

import com.xjzhang.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品评价回复关系
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "商品评价回复关系Dto")
public class CommentReplayDto extends BaseQuery {
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

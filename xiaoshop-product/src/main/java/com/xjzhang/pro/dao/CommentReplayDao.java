package com.xjzhang.pro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjzhang.pro.model.entity.CommentReplay;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Mapper
public interface  CommentReplayDao extends BaseMapper<CommentReplay> {

}

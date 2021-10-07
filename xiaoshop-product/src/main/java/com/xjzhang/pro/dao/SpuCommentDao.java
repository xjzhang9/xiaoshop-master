package com.xjzhang.pro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjzhang.pro.model.entity.SpuComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Mapper
public interface  SpuCommentDao extends BaseMapper<SpuComment> {

}

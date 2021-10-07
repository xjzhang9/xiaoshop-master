package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.SpuCommentDao;
import com.xjzhang.pro.model.entity.SpuComment;
import com.xjzhang.pro.service.SpuCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品评价
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("SpuCommentService")
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentDao, SpuComment> implements SpuCommentService {
   @Autowired
    private SpuCommentDao spuCommentDao;
}

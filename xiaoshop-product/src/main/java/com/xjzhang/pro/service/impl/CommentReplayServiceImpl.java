package com.xjzhang.pro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjzhang.pro.dao.CommentReplayDao;
import com.xjzhang.pro.model.entity.CommentReplay;
import com.xjzhang.pro.service.CommentReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品评价回复关系
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Service("CommentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplay> implements CommentReplayService {
   @Autowired
    private  CommentReplayDao commentReplayDao;
}

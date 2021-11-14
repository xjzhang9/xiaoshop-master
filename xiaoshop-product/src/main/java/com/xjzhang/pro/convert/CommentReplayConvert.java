package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xjzhang.pro.model.dto.CommentReplayDto;
import com.xjzhang.pro.model.entity.CommentReplay;
import com.xjzhang.pro.model.vo.CommentReplayVo;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;

/**
 * 商品评价回复关系 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class CommentReplayConvert {

    /**
    * entity -> vo
    * @return
    */
    public static CommentReplayVo entity2Vo(CommentReplay commentReplay) {
        if (commentReplay ==null){
            return null;
        }
            CommentReplayVo  commentReplayVo = new  CommentReplayVo();
        BeanUtils.copyProperties(commentReplay, commentReplayVo);

        return commentReplayVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static CommentReplay dto2Entity(CommentReplayDto commentReplayDto) {
        if (commentReplayDto == null) {
            return null;
        }
        CommentReplay  commentReplay =new  CommentReplay();
        BeanUtils.copyProperties(commentReplayDto,  commentReplay);

        return  commentReplay;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<CommentReplayVo> entity2VoList(List<CommentReplay> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<CommentReplayVo> listVo = new ArrayList<CommentReplayVo>();
        for (CommentReplay item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<CommentReplay> dto2EntityList(List<CommentReplayDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<CommentReplay> listEntity = new ArrayList<CommentReplay>();
        for (CommentReplayDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<CommentReplayVo> entity2VoPage(IPage<CommentReplay> page) {
        IPage<CommentReplayVo> vs = page.convert(item -> {
            try {
                return entity2Vo(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        return vs;
    }
}

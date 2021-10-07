package com.xjzhang.pro.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.ArrayList;
import com.xjzhang.pro.model.dto.SpuCommentDto;
import com.xjzhang.pro.model.vo.SpuCommentVo;
import com.xjzhang.pro.model.entity.SpuComment;

/**
 * 商品评价 bean 转换
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

public class SpuCommentConvert {

    /**
    * entity -> vo
    * @return
    */
    public static  SpuCommentVo entity2Vo(SpuComment spuComment) {
        if (spuComment ==null){
            return null;
        }
            SpuCommentVo  spuCommentVo = new  SpuCommentVo();
        BeanUtils.copyProperties(spuComment, spuCommentVo);

        return spuCommentVo;
    }

    /**
     * dto -> entity
     * @return
     */
    public static SpuComment dto2Entity(SpuCommentDto spuCommentDto) {
        if (spuCommentDto == null) {
            return null;
        }
        SpuComment  spuComment =new  SpuComment();
        BeanUtils.copyProperties(spuCommentDto,  spuComment);

        return  spuComment;
    }


    /**
     * entityList -> VoList
     * @return
     */
    public static List<SpuCommentVo> entity2VoList(List<SpuComment> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SpuCommentVo> listVo = new ArrayList<SpuCommentVo>();
        for (SpuComment item:
                          list) {
            listVo.add(entity2Vo(item));
        }
        return listVo;
    }


    /**
   * dtoList -> EntityList
   * @return
   */
    public static List<SpuComment> dto2EntityList(List<SpuCommentDto> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<SpuComment> listEntity = new ArrayList<SpuComment>();
        for (SpuCommentDto dto : list) {
            listEntity.add(dto2Entity(dto));
        }
        return listEntity;
    }

    /**
      * entityPage -> VoPage
      * @return
    */
    public static IPage<SpuCommentVo> entity2VoPage(IPage<SpuComment> page) {
        IPage<SpuCommentVo> vs = page.convert(item -> {
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

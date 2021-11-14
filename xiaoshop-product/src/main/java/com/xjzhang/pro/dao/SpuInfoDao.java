package com.xjzhang.pro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjzhang.pro.model.entity.SpuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */

@Mapper
public interface  SpuInfoDao extends BaseMapper<SpuInfo> {
    void upSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);
}

package com.xjzhang.pro.model.dto;

import com.xjzhang.base.group.AddGroup;
import com.xjzhang.base.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 品牌
 *
 * @Author xjzhang
 * @email xjzhang@163.com
 * @date 2021-10-05 17:10:35
 */
@EqualsAndHashCode
@Data
@ApiModel(value = "品牌Dto")
public class EditBrandDto  {
    /**
     *  品牌id
     */
    @NotBlank(message = "品牌id不能为空", groups = UpdateGroup.class)
    private Long brandId;
    /**
     *  介绍
     */
    private String descript;
    /**
     *  检索首字母
     */
    private String firstLetter;
    /**
     *  品牌logo地址
     */
    private String logo;
    /**
     *  品牌名
     */
    @NotBlank(message = "品牌id不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     *  显示状态[0-不显示；1-显示]
     */
    private Integer showStatus;
    /**
     *  排序
     */
    private Integer sort;
}

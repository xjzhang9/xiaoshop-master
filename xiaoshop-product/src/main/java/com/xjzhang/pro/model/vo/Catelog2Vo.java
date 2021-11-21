package com.xjzhang.pro.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 二级和三级分类vo
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/20 9:08
 */
@Data
public class Catelog2Vo {
    private Long catalog1ParentCid;
    private String name;
    private Long id;
    private List<Catelog3Vo> catalog3VoList;

    @Data
    public static class Catelog3Vo {
        private Long catalog1ParentCid;
        private String name;
        private String icon;
        private Long id;
    }
}

package com.xjzhang.pro.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/11/12 20:13
 */
@Data
public class SaveSpuInfoDto {
    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private int publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;
}

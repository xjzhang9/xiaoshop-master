package com.xjzhang.base.model.es;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/** sku es 信息
 * @author THTF
 */
@Data
public class SkuEsModel {
    private Long skuId;
    private Long spuId;
    private String skuTitle;
    private BigDecimal price;
    private String skuDefaultImg;
    private Long saleCount;
    private boolean hasStock;
    private Long hotScore;
    private Long brandId;
    private String brandName;
    private String brandImg;
    private Long catalogId;
    private String catalogName;
    private List<Attr> attrs;

    @Data
    public static class Attr {
        private Long attrId;
        private String attrName;
        private String attrValue;
    }
}

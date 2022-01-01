package com.xjzhang.cart.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/7 11:10
 */
@Data
@ConfigurationProperties(prefix = "xiaoshop")
public class XiaoShopProperties {
    AsyncTaskProperties task = new AsyncTaskProperties();
}

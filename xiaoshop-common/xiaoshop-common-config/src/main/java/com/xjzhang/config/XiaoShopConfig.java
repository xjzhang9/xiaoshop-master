package com.xjzhang.config;

import com.xjzhang.config.properties.XiaoShopProperties;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/7 10:55
 */
@Data
@EnableConfigurationProperties(XiaoShopProperties.class)
public class XiaoShopConfig {
}

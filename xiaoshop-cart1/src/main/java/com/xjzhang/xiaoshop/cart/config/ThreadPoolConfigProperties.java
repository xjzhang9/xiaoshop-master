package com.xjzhang.xiaoshop.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author THTF
 */
@ConfigurationProperties(prefix = "xiaoshop.thread")
@Data
public class ThreadPoolConfigProperties {
    private int corePoolSize = 50;
    private int maxPoolSize = 200 ;
    private long keepAliveTime = 3000;

}

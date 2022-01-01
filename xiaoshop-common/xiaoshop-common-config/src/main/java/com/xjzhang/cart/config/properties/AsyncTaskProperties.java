package com.xjzhang.cart.config.properties;

import lombok.Data;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/7 10:56
 */
@Data
public class AsyncTaskProperties {
    private int corePoolSize = 50;
    private int maxPoolSize = 100;
    private int queueCapacity = 10000;
    private int keepAliveSeconds = 3000;
    private String threadNamePrefix = "xiaoshop-task-executor-";
}

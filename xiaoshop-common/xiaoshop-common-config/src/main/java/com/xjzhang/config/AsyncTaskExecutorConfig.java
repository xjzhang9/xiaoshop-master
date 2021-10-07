package com.xjzhang.config;

import com.xjzhang.config.properties.XiaoShopProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/7 11:14
 */
@Configuration
@EnableAsync
@EnableScheduling
@Slf4j
public class AsyncTaskExecutorConfig implements AsyncConfigurer {
    @Resource
    private XiaoShopProperties properties;

    @Bean("taskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        log.debug("开启异步线程池");
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(properties.getTask().getCorePoolSize());
        taskExecutor.setMaxPoolSize(properties.getTask().getMaxPoolSize());
        taskExecutor.setQueueCapacity(properties.getTask().getQueueCapacity());
        taskExecutor.setKeepAliveSeconds(properties.getTask().getKeepAliveSeconds());
        taskExecutor.setThreadNamePrefix(properties.getTask().getThreadNamePrefix());

        return new ExceptionHandlingAsyncTaskExecutor(taskExecutor);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}

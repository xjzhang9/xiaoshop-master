package com.xjzhang.cart.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/12/12 9:31
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.56.11:6379");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}

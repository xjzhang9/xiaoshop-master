package com.xjzhang.shiro.cache;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/3 9:13
 */
public class ShiroCacheManager implements CacheManager {
    private RedisTemplate<String, Object> redisTemplate;

    public ShiroCacheManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Cache getCache(String s) {
        return new ShiroCache(redisTemplate);
    }
}

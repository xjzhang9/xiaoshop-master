package com.xjzhang.shiro.cache;

import com.xjzhang.base.constant.RedisConstant;
import com.xjzhang.utils.JwtUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/10/3 8:55
 */
public class ShiroCache<K, V> implements Cache<K, V> {
    private String shiroCacheExpireTime = "600";

    private RedisTemplate<String, Object> redisTemplate;

    public ShiroCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String getKey(Object key) {
        return RedisConstant.PREFIX_SHIRO_CACHE + ":" + JwtUtil.getClaim(key.toString(), "account");
    }


    @Override
    public Object get(Object k) throws CacheException {
        return redisTemplate.opsForValue().get(getKey(k));
    }

    @Override
    public Object put(Object k, Object v) throws CacheException {
        redisTemplate.opsForValue().set(getKey(k), v, Integer.parseInt(shiroCacheExpireTime), TimeUnit.MINUTES);

        return true;
    }

    @Override
    public Object remove(Object k) throws CacheException {
        redisTemplate.delete(getKey(k));

        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}

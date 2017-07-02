package com.song.cache.cache;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisOperations;

/**
 * Created by Song on 2017/7/2.
 */
public class ObjectRedisCache1 extends RedisCache {
    public ObjectRedisCache1(String name, byte[] prefix, RedisOperations<? extends Object, ? extends Object> redisOperations, long expiration) {
        super(name, prefix, redisOperations, expiration);
    }

    public ObjectRedisCache1(String name, byte[] prefix, RedisOperations<? extends Object, ? extends Object> redisOperations, long expiration, boolean allowNullValues) {
        super(name, prefix, redisOperations, expiration, allowNullValues);
    }
}

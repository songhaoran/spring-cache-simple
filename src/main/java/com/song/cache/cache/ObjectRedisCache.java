package com.song.cache.cache;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * Created by Song on 2017/6/30.
 */
public class ObjectRedisCache implements Cache {
    private Logger log = LogManager.getLogger(this.getClass());

    private String name;

    private RedisTemplate redisTemplate;

    @Override
    public String getName() {
        return "objectRedisCache";
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        try {
            Map map = redisTemplate.boundHashOps(key).entries();
            T t = type.newInstance();
            BeanUtils.populate(t, map);
            return t;
        } catch (Exception e) {
            log.debug("ObjectRedisCache-get", e);
            return null;
        }
    }

    @Override
    public void put(Object key, Object value) {
        Map map = new BeanMap(value);
        redisTemplate.boundHashOps(key).putAll(map);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {
        redisTemplate.boundHashOps(key).delete();
    }

    @Override
    public void clear() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}

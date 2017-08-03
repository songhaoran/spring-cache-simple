package com.song.cache.service;

import com.song.cache.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Song on 2017/7/2.
 */
@Service
public class UserService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Cacheable(value = {"redisCache","objectRedisCache1"})
    @Transactional(rollbackFor = {Exception.class})
    public User getUser(String userName) {
        User user = new User(userName, 10);
        System.out.println("***********" + this.getClass().getName() + ":" + userName + "**********");
        int i = 1 / 0;
        return user;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void save() {
        final User user = new User("song", 100);
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set("song".getBytes(),user.toString().getBytes());
                return null;
            }
        });
        int i = 1 / 0;
    }

    public User fromMethod(String userName) {
        return getUser(userName);
    }
}

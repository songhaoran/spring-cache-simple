package com.song.cache.service;

import com.song.cache.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by Song on 2017/7/2.
 */
@Service
public class UserService {

    @Cacheable(value = {"redisCache","objectRedisCache1"})
    public User getUser(String userName) {
        User user = new User(userName, 10);
        System.out.println("***********" + this.getClass().getName() + ":" + userName + "**********");
        return user;
    }

    public User fromMethod(String userName) {
        return getUser(userName);
    }
}

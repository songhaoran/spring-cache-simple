package com.song.cache.service;

import com.song.cache.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by Song on 2017/7/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {
      /*  User song = userService.fromMethod("song");
        System.out.println("song[0]:"+song.toString());
        User song1 = userService.fromMethod("song");
        System.out.println("song[1]:"+song1.toString());
*/
        User song= userService.getUser("song");
        System.out.println("song:"+song.toString());
        User jia = userService.getUser("jia");
        System.out.println("jia:"+jia.toString());
    }

    @Test
    public void testSave() {
        userService.save();
    }
}

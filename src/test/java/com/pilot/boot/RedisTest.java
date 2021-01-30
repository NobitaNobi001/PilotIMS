package com.pilot.boot;

import com.pilot.boot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author ezuy
 * @date 21/1/22 18:45
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testInsertUser(){

        User user = new User();
        user.setType("1");
        user.setUserName("user11");
        user.setSex(1);
        user.setPassword("11111111");
        user.setCard("111111111111111111");
        user.setDeptId(Long.valueOf(1));
        user.setPosition("教授");
        user.setJobTitle("军官");
        user.setPhone("12345678911");
        user.setEmail("frank.ezuy@qq.com");
        user.setRemark("无");

        redisTemplate.opsForList().rightPush(1,user);
    }
}

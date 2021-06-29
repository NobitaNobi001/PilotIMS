package com.pilot.boot;

import com.pilot.boot.entity.User;
import com.pilot.boot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * test user operation
 * @author ezuy
 * @date 21/1/21 13:29
 */
@Slf4j
@SpringBootTest
public class UserTest {


    @Resource
    private UserService userService;

//    @org.junit.jupiter.api.Test
    public void testInsertUser() {
        String card = "111111111111111111";

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            User user = new User();

            user.setType("1");
            user.setUserName("user" + i);
            user.setSex(1);
            user.setPassword("11111111");
            user.setCard(card);
            user.setDeptId(Long.valueOf(1));
            user.setPosition("教授");
            user.setJobTitle("军官");
            user.setPhone("12345678911");
            user.setEmail("frank.ezuy@qq.com");
            user.setRemark("无");
            userService.addUser(user);
        }

        long end = System.currentTimeMillis();

        log.info(String.valueOf(end - start));

    }
}
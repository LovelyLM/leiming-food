package com.leiming.food;

import cn.hutool.core.bean.BeanUtil;
import com.leiming.food.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FoodApplicationTests {

    @Test
    void contextLoads() {
        User leiming = User.builder().build().setId(1).setPassword("123").setUsername("leiming");
        User user = User.builder().build().setPersonalizedSignature("签名");
        BeanUtil.copyProperties(user, leiming);
        System.out.println(leiming);


    }

}

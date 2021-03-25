package com.leiming.food;

import cn.hutool.core.bean.BeanUtil;
import com.leiming.food.entity.User;
import com.leiming.food.service.MallProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class FoodApplicationTests {
    @Resource
    private MallProductService productService;
    @Test
    void contextLoads() {


    }
    @Test
    void mapQueryTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("id",2);
        map.put("price",50);
        log.info(String.valueOf(productService.listByMap(map)));
    }

}

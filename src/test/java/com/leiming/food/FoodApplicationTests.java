package com.leiming.food;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.leiming.food.entity.User;
import com.leiming.food.service.MallProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class FoodApplicationTests {
    @Resource
    private MallProductService productService;
    @Test
    void contextLoads() {
        String time = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        log.info(time + NumberUtil.generateRandomNumber(1000000, 10000000, 1)[0]);

    }
    @Test
    void mapQueryTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("id",2);
        map.put("price",50);
        log.info(String.valueOf(productService.listByMap(map)));
    }

}

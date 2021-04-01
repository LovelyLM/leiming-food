package com.leiming.food.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author LovelyLM
 * @date 2021-04-01 23:40
 */
@Data
public class MyPage<T>{

    private List<T> records = Collections.emptyList();

    protected long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    protected long size = 10;

    /**
     * 当前页
     */
    protected long current = 1;


}

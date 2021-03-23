package com.leiming.food.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.entity.MallCart;
import com.leiming.food.entity.User;
import com.leiming.food.service.MallCartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 购物车(MallCart)表控制层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:56
 */
@RestController
@RequestMapping("mallCart")
public class MallCartController {
    /**
     * 服务对象
     */
    @Resource
    private MallCartService mallCartService;





}

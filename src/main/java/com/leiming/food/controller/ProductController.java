package com.leiming.food.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.entity.MallProduct;
import com.leiming.food.service.MallProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 商品表(MallProduct)表控制层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:00
 */
@RestController(value = "ProductController")
@RequestMapping("product")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private MallProductService productService;

    @GetMapping("/detail")
    public ApiRestResponse getDetail(@RequestParam Long id){
        MallProduct product = productService.getById(id);
        return ApiRestResponse.success(product);
    }

    @GetMapping("/list")
    public ApiRestResponse getDetailList(MallProduct product){
        LambdaQueryWrapper<MallProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MallProduct::getId,"id").eq(MallProduct::getName, "name");
     

        return ApiRestResponse.success(product);
    }


}

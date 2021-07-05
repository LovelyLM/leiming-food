package com.leiming.food.controller;


import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.entity.vo.CategoryVo;
import com.leiming.food.service.MallCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LovelyLM
 * @date 2021-03-21 18:35
 */
@RestController("category")
@RequestMapping("category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private MallCategoryService mallCategoryService;

    @ApiOperation("分类查询")
    @GetMapping("find_all_category")
    public ApiRestResponse findCategoryPage(){
        List<CategoryVo> list = new ArrayList<>();
        mallCategoryService.getAllCategory(list, 0L);
        return ApiRestResponse.success(list);
    }



}

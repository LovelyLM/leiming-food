package com.leiming.food.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.entity.MallCategory;
import com.leiming.food.entity.vo.CategoryVo;
import com.leiming.food.service.MallCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LovelyLM
 * @date 2021-03-21 18:35
 */
@RestController("category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private MallCategoryService mallCategoryService;

    @ApiOperation("分类分页查询")
    @GetMapping("find_all_category")
    public ApiRestResponse findCategoryPage(){
        List<CategoryVo> list = new ArrayList<>();
        mallCategoryService.getAllCategory(list, 0L);
        return ApiRestResponse.success(list);
    }



}

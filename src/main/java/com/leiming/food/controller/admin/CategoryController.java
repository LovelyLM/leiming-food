package com.leiming.food.controller.admin;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.common.Constant;
import com.leiming.food.common.ResultCode;
import com.leiming.food.entity.MallCategory;
import com.leiming.food.entity.User;
import com.leiming.food.entity.param.CategoryAddParam;
import com.leiming.food.exception.MallException;
import com.leiming.food.service.MallCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 商品分类 (MallCategory)表控制层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:58
 */
@RestController(value = "AdminCategoryController")
@RequestMapping("admin/category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private MallCategoryService mallCategoryService;



    @ApiOperation("分类分页查询")
    @GetMapping("find_page")
    public ApiRestResponse findCategoryPage(Page page){
        Page categoryPage = mallCategoryService.page(page);
        return ApiRestResponse.success(categoryPage);
    }

    @ApiOperation("更新分类")
    @PostMapping("update")
    public ApiRestResponse updateCategory(@RequestBody @Valid CategoryAddParam categoryAddParam){
        mallCategoryService.updateCategory(categoryAddParam);
        return ApiRestResponse.success();
    }

    @ApiOperation("删除分类")
    @PostMapping("delete")
    public ApiRestResponse deleteCategory(Long id){
        if (ObjectUtil.isEmpty(id)){
            return ApiRestResponse.error(500, "id不能为空");
        }
        MallCategory category = mallCategoryService.getById(id);
        if (ObjectUtil.isEmpty(category)){
            throw new MallException(ResultCode.DELETE_FAILED);
        }
        mallCategoryService.removeById(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("添加分类")
    @PostMapping("add")
    public ApiRestResponse addCategory(@RequestBody @Valid CategoryAddParam categoryAddParam){
        QueryWrapper<MallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", categoryAddParam.getName());
        int count = mallCategoryService.count(queryWrapper);
        if (count != 0){
            throw new MallException(ResultCode.CATEGORY_REPEAT);
        }
        MallCategory category = MallCategory.builder().build().setCreateTime(new Date());
        BeanUtil.copyProperties(categoryAddParam, category);
        mallCategoryService.save(category);
        return ApiRestResponse.success();
    }
}

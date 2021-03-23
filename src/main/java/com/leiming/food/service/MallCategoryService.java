package com.leiming.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leiming.food.entity.MallCategory;
import com.leiming.food.entity.param.CategoryAddParam;
import com.leiming.food.entity.vo.CategoryVo;

import java.util.List;

/**
 * 商品分类 (MallCategory)表服务接口
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:57
 */
public interface MallCategoryService extends IService<MallCategory> {
    /**
     * 更新 category
     * @param categoryAddParam 实体
     */
    void updateCategory(CategoryAddParam categoryAddParam);

    /**
     * 获取所有层级分类
     * @param list
     * @param parentId
     */
    void getAllCategory(List<CategoryVo> list, Long parentId);

}

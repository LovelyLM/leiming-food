package com.leiming.food.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.common.Constant;
import com.leiming.food.common.ResultCode;
import com.leiming.food.dao.MallCategoryDao;
import com.leiming.food.entity.MallCategory;
import com.leiming.food.entity.param.CategoryAddParam;
import com.leiming.food.entity.vo.CategoryVo;
import com.leiming.food.service.MallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品分类 (MallCategory)表服务实现类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:57
 */
@Service("mallCategoryService")
public class MallCategoryServiceImpl extends ServiceImpl<MallCategoryDao, MallCategory> implements MallCategoryService {
    @Autowired
    private MallCategoryDao mallCategoryDao;

    @Override
    public void updateCategory(CategoryAddParam categoryAddParam) {
        MallCategory category = MallCategory.builder().build();
        BeanUtil.copyProperties(categoryAddParam, category);
        mallCategoryDao.updateById(category);
    }

    @Override
    @Cacheable(value = "getAllCategory")
    public void getAllCategory(List<CategoryVo> list, Long parentId){
        List<MallCategory> categoryList = mallCategoryDao.selectList(new QueryWrapper<MallCategory>().eq("parent_id", parentId));
        if (CollectionUtil.isNotEmpty(categoryList)){
            categoryList.forEach(e->{
                CategoryVo categoryVo = new CategoryVo();
                BeanUtil.copyProperties(e, categoryVo);
                list.add(categoryVo);
                getAllCategory(categoryVo.getChildren(), categoryVo.getId());
            });
        }
    }
}

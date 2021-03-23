package com.leiming.food.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leiming.food.entity.MallCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 商品分类 (MallCategory)表数据库访问层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:57
 */
public interface MallCategoryDao extends BaseMapper<MallCategory> {

}

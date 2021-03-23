package com.leiming.food.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leiming.food.entity.MallProduct;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 商品表(MallProduct)表数据库访问层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:00
 */
public interface MallProductDao extends BaseMapper<MallProduct> {

}

package com.leiming.food.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leiming.food.entity.MallCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 购物车(MallCart)表数据库访问层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:54
 */
public interface MallCartDao extends BaseMapper<MallCart> {

}

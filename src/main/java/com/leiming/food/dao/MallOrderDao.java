package com.leiming.food.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leiming.food.entity.MallOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 订单表;(MallOrder)表数据库访问层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:58
 */
public interface MallOrderDao extends BaseMapper<MallOrder> {

}

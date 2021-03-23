package com.leiming.food.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leiming.food.entity.MallOrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 订单的商品表 (MallOrderItem)表数据库访问层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:59
 */
public interface MallOrderItemDao extends BaseMapper<MallOrderItem> {

}

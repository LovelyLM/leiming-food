package com.leiming.food.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leiming.food.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 用户表 (MallUser)表数据库访问层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:01
 */
public interface MallUserDao extends BaseMapper<User> {

}

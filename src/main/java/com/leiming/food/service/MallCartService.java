package com.leiming.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leiming.food.entity.MallCart;
import com.leiming.food.entity.vo.CartVo;


import java.util.List;

/**
 * 购物车(MallCart)表服务接口
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:55
 */
public interface MallCartService extends IService<MallCart> {

    /**
     * 添加购物车
     * @param userId 用户id
     * @param productId  商品id
     * @param count 数量
     * @return 返回值
     */
    void add(Long userId, Long productId, Integer count);

    /**
     * 查询所有用户的购物车
     * @param userId 用户id
     * @return 返回值
     */
    List<CartVo> getCarList(Long userId);

    /**
     * 删除购物车
     * @param userId 用户id
     * @param productId 商品id
     */
    void deleteCart(Long userId, Long productId);

    /**
     * 选中购物车
     * @param userId 用户id
     * @param productId 商品id
     * @param select 选中状态
     * @return 返回值
     */
    List<CartVo> selectOrNot(Long userId, Long productId, Integer select);

    /**
     * 全选购物车
     * @param userId 用户id
     * @param select 选中状态
     * @return 返回值
     */
    List<CartVo> selectAllOrNot(Long userId, Integer select);






}

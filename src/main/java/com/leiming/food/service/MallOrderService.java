package com.leiming.food.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leiming.food.entity.MallOrder;
import com.leiming.food.entity.param.OrderAddParam;
import com.leiming.food.entity.vo.OrderVo;

/**
 * 订单表;(MallOrder)表服务接口
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:58
 */
public interface MallOrderService extends IService<MallOrder> {


    /**
     * 创建订单
     * @param param
     * @return
     */
    String createOder(OrderAddParam param);


    /**
     * 查询用户订单详情
     * @param orderNo
     * @return
     */
    OrderVo getOrderDetail(String orderNo);

    /**
     * 获取订单列表
     * @param page
     * @return
     */
    Page<OrderVo> getOrderDetailList(Page<MallOrder> page);

    /**
     * 获取所有订单列表
     * @param page
     * @return
     */
    Page<OrderVo> getAllOrderDetailList(Page<MallOrder> page);


    /**
     * 取消订单
     * @param orderNo
     */
    void orderCancel(String orderNo);

    /**
     * 支付
     * @param orderNo
     */
    void pay(String orderNo);

    /**
     * 发货
     * @param orderNo
     */
    void delivered(String orderNo);

    /**
     * 完成订单
     * @param orderNo
     */
    void finished(String orderNo);
}

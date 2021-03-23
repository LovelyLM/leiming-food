package com.leiming.food.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leiming.food.dao.MallOrderDao;
import com.leiming.food.entity.MallOrder;
import com.leiming.food.service.MallOrderService;
import org.springframework.stereotype.Service;

/**
 * 订单表;(MallOrder)表服务实现类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:58
 */
@Service("mallOrderService")
public class MallOrderServiceImpl extends ServiceImpl<MallOrderDao, MallOrder> implements MallOrderService {

}

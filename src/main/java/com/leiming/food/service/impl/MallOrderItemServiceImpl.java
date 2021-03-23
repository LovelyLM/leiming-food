package com.leiming.food.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leiming.food.dao.MallOrderItemDao;
import com.leiming.food.entity.MallOrderItem;
import com.leiming.food.service.MallOrderItemService;
import org.springframework.stereotype.Service;

/**
 * 订单的商品表 (MallOrderItem)表服务实现类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:59
 */
@Service("mallOrderItemService")
public class MallOrderItemServiceImpl extends ServiceImpl<MallOrderItemDao, MallOrderItem> implements MallOrderItemService {

}

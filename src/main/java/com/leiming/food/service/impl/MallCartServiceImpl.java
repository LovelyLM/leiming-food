package com.leiming.food.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leiming.food.dao.MallCartDao;
import com.leiming.food.entity.MallCart;
import com.leiming.food.service.MallCartService;
import org.springframework.stereotype.Service;

/**
 * 购物车(MallCart)表服务实现类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:55
 */
@Service("mallCartService")
public class MallCartServiceImpl extends ServiceImpl<MallCartDao, MallCart> implements MallCartService {

}

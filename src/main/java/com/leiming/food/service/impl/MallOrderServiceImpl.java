package com.leiming.food.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leiming.food.common.Constant;
import com.leiming.food.common.ResultCode;
import com.leiming.food.dao.MallOrderDao;
import com.leiming.food.entity.MallOrder;
import com.leiming.food.entity.MallOrderItem;
import com.leiming.food.entity.MallProduct;
import com.leiming.food.entity.param.OrderAddParam;
import com.leiming.food.entity.vo.CartVo;
import com.leiming.food.entity.vo.OrderItemVo;
import com.leiming.food.entity.vo.OrderVo;
import com.leiming.food.exception.MallException;
import com.leiming.food.service.MallCartService;
import com.leiming.food.service.MallOrderItemService;
import com.leiming.food.service.MallOrderService;
import com.leiming.food.service.MallProductService;
import com.leiming.food.utils.filter.UserFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单表;(MallOrder)表服务实现类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:58
 */
@Service("mallOrderService")
public class MallOrderServiceImpl extends ServiceImpl<MallOrderDao, MallOrder> implements MallOrderService {

    @Resource
    private MallCartService cartService;

    @Resource
    private MallProductService productService;

    @Resource
    private MallOrderItemService orderItemService;


    /**
     * 创建订单
     * @param param 订单添加实体
     * @return 返回订单No.
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOder(OrderAddParam param) {

        //获取登录用户id
        Long userId = UserFilter.currentUser.getId();

        //获取用户购物车（默认只获取已选择的购物车）
        List<CartVo> cartVoList = cartService.getCarList(userId);

        //判断用户已选择的购物车商品是否为空
        if (CollectionUtil.isEmpty(cartVoList)){
            throw new MallException(ResultCode.CART_ITEM_NONE);
        }

        //验证购物车里面的商品状态与库存
        cartVoList.forEach(cart-> validProduct(cart.getProductId(), cart.getQuantity()));

        //生成订单No.
        String time = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        String orderNo = time + NumberUtil.generateRandomNumber(1000000, 10000000, 1)[0];

        //生成订单item并更新库存
        List<MallOrderItem> orderItemList = cartVoList.stream().map(cartVo -> {
            //查找原来的商品
            MallProduct product = productService.getById(cartVo.getProductId());
            //更新库存
            product.setStock(product.getStock() - cartVo.getQuantity());
            productService.updateById(product);
            //删除购物车中的商品
            cartService.removeById(cartVo.getId());
            //生成订单item
            return MallOrderItem.builder().build()
                    .setTotalPrice(cartVo.getTotalPrice()).setProductId(cartVo.getProductId())
                    .setProductImg(cartVo.getProductImage()).setQuantity(cartVo.getQuantity())
                    .setProductName(cartVo.getProductName()).setOrderNo(orderNo).setUnitPrice(cartVo.getPrice());
        }).collect(Collectors.toList());

        //批量保存订单item
        orderItemService.saveBatch(orderItemList);

        //计算总价，并初始化订单数据
        BigDecimal sumPrice = orderItemList.stream().map(MallOrderItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        MallOrder order = MallOrder.builder().build().setReceiverAddress(param.getReceiverAddress()).setReceiverMobile(param.getReceiverMobile()).setOrderNo(orderNo)
                .setTotalPrice(sumPrice).setUserId(userId).setOrderStatus(Constant.ORDER_NOT_PAY).setPostage(0).setPaymentType(1).setReceiverName(param.getReceiverName());

        //保存订单
        this.save(order);

        //返回订单No.
        return orderNo;
    }



    @Override
    public OrderVo getOrderDetail(String orderNo) {

        //获取用户id
        Long userId = UserFilter.currentUser.getId();

        //根据订单No.查询订单
        QueryWrapper<MallOrder> queryWrapperOrder = new QueryWrapper<>();
        queryWrapperOrder.eq("order_no", orderNo);
        MallOrder order = this.getOne(queryWrapperOrder);

        //判断订单是否存在
        if (ObjectUtil.isEmpty(order)){
            throw new MallException(ResultCode.ORDER_NOT_FOUND);
        }

        //判断订单是否属于当前用户
        if (ObjectUtil.notEqual(userId, order.getUserId())){
            throw new MallException(ResultCode.ORDER_NOT_FOUND);
        }

//        //查询订单item
//        QueryWrapper<MallOrderItem> queryWrapperOrderItem = new QueryWrapper<>();
//        queryWrapperOrderItem.eq("order_no", orderNo);
//        List<MallOrderItem> orderItemList = orderItemService.list(queryWrapperOrderItem);
//
//        //判断订单item是否存在
//        if (CollectionUtil.isEmpty(orderItemList)){
//            throw new MallException(ResultCode.ORDER_ITEM_NOT_FOUND);
//        }
//
//        //封装orderVo数据
//        OrderVo orderVo = new OrderVo();
//        BeanUtil.copyProperties(order, orderVo);
//        List<OrderItemVo> orderItemVoList = orderItemList.stream().map(orderItem -> {
//            OrderItemVo orderItemVo = new OrderItemVo();
//            BeanUtil.copyProperties(orderItem, orderItemVo);
//            return orderItemVo;
//        }).collect(Collectors.toList());
//        orderVo.setOrderItemVoList(orderItemVoList);


        return  getOrderByOrderNo(order, orderNo);
    }



    @Override
    public Page<OrderVo> getOrderDetailList(Page<MallOrder> page) {
        //获取用户id
        Long userId = UserFilter.currentUser.getId();

        //根据用户id查询订单
        QueryWrapper<MallOrder> queryWrapperOrder = new QueryWrapper<>();
        queryWrapperOrder.eq("user_id", userId).orderByDesc("create_time");
        Page<MallOrder> orderPage = this.page(page, queryWrapperOrder);
        List<MallOrder> orderList = orderPage.getRecords();
        //若没有订单，直接返回
        if (CollectionUtil.isEmpty(orderList)){
            Page<OrderVo> orderVoPage = new Page<>();
            BeanUtil.copyProperties(orderPage, orderVoPage);
            return orderVoPage;
        }
        List<OrderVo> orderVoList = orderList.stream().map(order -> getOrderByOrderNo(order, order.getOrderNo())).collect(Collectors.toList());
        Page<OrderVo> orderVoPage = new Page<>();
        BeanUtil.copyProperties(orderPage, orderVoPage);
        orderVoPage.setRecords(orderVoList);


        return orderVoPage;
    }


    /**
     * 验证商品时候存在（包括是否上架）和商品库存是否足够
     * @param productId 商品id
     * @param count 商品数量
     */
    private void validProduct(Long productId, Integer count) {
        //获取商品
        MallProduct product = productService.getById(productId);
        //判断商品是否不存在以及上下架情况
        if (ObjectUtil.isEmpty(product) || ObjectUtil.equals(product.getStatus(), Constant.PRODUCT_DOWN)){
            throw new MallException(ResultCode.PRODUCT_NOT_FOUND);
        }
        //判断商品库存是否不足
        if (count > product.getStock()){
            throw new MallException(ResultCode.PRODUCT_NO_STOCK);
        }
    }


    private OrderVo getOrderByOrderNo(MallOrder order, String orderNo){
        //查询订单item
        QueryWrapper<MallOrderItem> queryWrapperOrderItem = new QueryWrapper<>();
        queryWrapperOrderItem.eq("order_no", orderNo).orderByDesc("create_time");
        List<MallOrderItem> orderItemList = orderItemService.list(queryWrapperOrderItem);

        //判断订单item是否存在
        if (CollectionUtil.isEmpty(orderItemList)){
            throw new MallException(ResultCode.ORDER_ITEM_NOT_FOUND);
        }

        //封装orderVo数据
        OrderVo orderVo = new OrderVo();
        BeanUtil.copyProperties(order, orderVo);
        List<OrderItemVo> orderItemVoList = orderItemList.stream().map(orderItem -> {
            OrderItemVo orderItemVo = new OrderItemVo();
            BeanUtil.copyProperties(orderItem, orderItemVo);
            return orderItemVo;
        }).collect(Collectors.toList());
        orderVo.setOrderItemVoList(orderItemVoList);
        return orderVo;
    }
}

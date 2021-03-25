package com.leiming.food.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leiming.food.common.Constant;
import com.leiming.food.common.ResultCode;
import com.leiming.food.dao.MallCartDao;
import com.leiming.food.dao.MallProductDao;
import com.leiming.food.entity.MallCart;
import com.leiming.food.entity.MallProduct;
import com.leiming.food.entity.vo.CartVo;
import com.leiming.food.exception.MallException;
import com.leiming.food.service.MallCartService;
import com.leiming.food.service.MallProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车(MallCart)表服务实现类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:55
 */
@Service("mallCartService")
public class MallCartServiceImpl extends ServiceImpl<MallCartDao, MallCart> implements MallCartService {
    @Resource
    private MallProductService productService;

    /**
     * 添加商品到购物车
     * @param userId 用户id
     * @param productId  商品id
     * @param count 数量
     * @return 返回购物车列表
     */
    @Override
    public void add(Long userId, Long productId, Integer count) {
        validProduct(productId, count);
        QueryWrapper<MallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(e->e.eq("product_id",productId).eq("user_id", userId));

        MallCart mallCart = this.getOne(queryWrapper);


        if (ObjectUtil.isEmpty(mallCart)){
            if (count < 0){
                throw new MallException(ResultCode.CART_NOT_FOUND);
            }
            mallCart = new MallCart();
            mallCart.setUserId(userId);
            mallCart.setProductId(productId);
            mallCart.setSelected(1);
            mallCart.setQuantity(count);
            save(mallCart);
        }else {
            count = mallCart.getQuantity() + count;
            if (count < 0){
                count = 0;
            }
            mallCart.setQuantity(count);
            mallCart.setQuantity(count);
            mallCart.setSelected(1);
            this.updateById(mallCart);
        }

    }

    @Override
    public List<CartVo> getCarList(Long userId) {
        QueryWrapper<MallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(e->e.eq("user_id", userId).gt("quantity",0));
        List<MallCart> cartList = list(queryWrapper);
        return cartList.stream().map(cart -> {
            MallProduct product = productService.getById(cart.getProductId());
            CartVo cartVo = CartVo.builder().build().setProductName(product.getName())
                    .setProductImage(product.getImage()).setPrice(product.getPrice())
                    .setTotalPrice(product.getPrice().multiply(new BigDecimal(cart.getQuantity())));
            BeanUtil.copyProperties(cart, cartVo);

            return cartVo;
        }).collect(Collectors.toList());

    }

    @Override
    public void deleteCart(Long userId, Long productId) {
        QueryWrapper<MallCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        if (!ObjectUtil.isEmpty(productId)) {
            queryWrapper.eq("product_id", productId);
            if (ObjectUtil.isEmpty(getOne(queryWrapper))){
                throw new MallException(ResultCode.CART_NOT_FOUND);
            }
        }
        if (ObjectUtil.isEmpty(getOne(queryWrapper))){
            throw new MallException(ResultCode.CART_NOT_FOUND);
        }
        this.remove(queryWrapper);
    }


    /**
     * 验证商品
     * @param productId
     * @param count
     */
    private void validProduct(Long productId, Integer count) {
        MallProduct product = productService.getById(productId);
        if (ObjectUtil.isEmpty(product) || ObjectUtil.equals(product.getStatus(), Constant.PRODUCT_DOWN)){
            throw new MallException(ResultCode.PRODUCT_NOT_FOUND);
        }
        if (count > product.getStock()){
            throw new MallException(ResultCode.PRODUCT_NO_STOCK);

        }
    }
}

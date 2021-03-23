package com.leiming.food.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leiming.food.common.ResultCode;
import com.leiming.food.dao.MallProductDao;
import com.leiming.food.entity.MallProduct;
import com.leiming.food.entity.param.ProductUpdateParam;
import com.leiming.food.exception.MallException;
import com.leiming.food.service.MallProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品表(MallProduct)表服务实现类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:00
 */
@Service("mallProductService")
public class MallProductServiceImpl extends ServiceImpl<MallProductDao, MallProduct> implements MallProductService {
    @Autowired
    private MallProductDao mallProductDao;

    @Override
    public void updateProduct(ProductUpdateParam param) {

        QueryWrapper<MallProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", param.getName());
        MallProduct product = mallProductDao.selectOne(queryWrapper);
        if ( ObjectUtil.isNotEmpty(product) && ObjectUtil.notEqual(param.getId(), product.getId())){
            throw new MallException(ResultCode.PRODUCT_NAME_REPEAT);
        }
        MallProduct mallProduct = new MallProduct();
        BeanUtil.copyProperties(param, mallProduct);
        mallProductDao.updateById(mallProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (ObjectUtil.isEmpty(id)){
            throw new MallException(ResultCode.PARAM_IS_BLANK);
        }
        MallProduct product = mallProductDao.selectById(id);
        if (ObjectUtil.isEmpty(product)){
            throw new MallException(ResultCode.PRODUCT_NOT_FOUND);
        }
        mallProductDao.deleteById(id);
    }

    @Override
    public void batchUpdateStatus(Long[] ids, Integer status) {
        if (ObjectUtil.isEmpty(ids)){
            throw new MallException(ResultCode.PARAM_IS_BLANK);
        }
        if (ObjectUtil.notEqual(status, 0) && ObjectUtil.notEqual(status , 1)){
            throw new MallException(ResultCode.PARAM_IS_INVALID);
        }
        MallProduct product = new MallProduct();
        product.setStatus(status);
        for (Long id : ids) {
            product.setId(id);
            mallProductDao.updateById(product);
        }
    }
}

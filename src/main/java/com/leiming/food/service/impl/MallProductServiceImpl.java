package com.leiming.food.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leiming.food.common.ResultCode;
import com.leiming.food.dao.MallProductDao;
import com.leiming.food.entity.MallProduct;
import com.leiming.food.entity.param.ProductListParam;
import com.leiming.food.entity.param.ProductUpdateParam;
import com.leiming.food.entity.vo.CategoryVo;
import com.leiming.food.exception.MallException;
import com.leiming.food.service.MallCategoryService;
import com.leiming.food.service.MallProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品表(MallProduct)表服务实现类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:00
 */
@Service("mallProductService")
public class MallProductServiceImpl extends ServiceImpl<MallProductDao, MallProduct> implements MallProductService {
    @Resource
    private MallProductDao mallProductDao;

    @Resource
    private MallCategoryService categoryService;

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

    @Override
    public Page<MallProduct> getList(ProductListParam param) {
        return page(param, getQueryWrapper(param));
    }


    private QueryWrapper<MallProduct> getQueryWrapper(ProductListParam param) {
        QueryWrapper<MallProduct> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(param.getId())){
            queryWrapper.eq("id", param.getId());
            return queryWrapper;
        }


        if (ObjectUtil.isNotEmpty(param.getCategoryId())){
            List<CategoryVo> categoryVoList = new ArrayList<>();
            categoryService.getAllCategory(categoryVoList, param.getCategoryId());
            List<Long> categoryIds = new ArrayList<>();
            categoryIds.add(param.getCategoryId());
            getAllCategoryId(categoryVoList, categoryIds);
            queryWrapper.and(wrapper->{
                for (int i = 0; i < categoryIds.size(); i++) {
                    if (i == 0){
                        wrapper.eq("category_id", categoryIds.get(i));
                    }else {
                        wrapper.or().eq("category_id", categoryIds.get(i));
                    }
                }
            });
        }

        if (ObjectUtil.isNotEmpty(param.getStatus())){
            queryWrapper.and(e->e.like("status", param.getStatus()));
        }

        if (ObjectUtil.isNotEmpty(param.getName())){
            queryWrapper.and(e->e.like("name", param.getName()));

        }
        if (ObjectUtil.isNotEmpty(param.getDetail())){
            queryWrapper.and(e->e.like("detail", param.getDetail()));
        }
        if (ObjectUtil.isNotEmpty(param.getUpOrDown()) && ObjectUtil.isNotEmpty(param.getOrderBy())){
            if (param.getUpOrDown() == 1){
                queryWrapper.orderByAsc(param.getOrderBy());
            }
            if (param.getUpOrDown() == 0){
                queryWrapper.orderByDesc(param.getOrderBy());
            }
        }

        return queryWrapper;
    }

    private void getAllCategoryId(List<CategoryVo> categoryVoList, List<Long> categoryIds){
        categoryVoList.forEach(e->{
            if (ObjectUtil.isNotEmpty(e)){
                categoryIds.add(e.getId());
            }
            if (ObjectUtil.isNotEmpty(e.getChildren())){
                getAllCategoryId(e.getChildren(), categoryIds);
            }
        });
    }
}

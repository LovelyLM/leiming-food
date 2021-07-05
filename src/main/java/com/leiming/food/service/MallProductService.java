package com.leiming.food.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leiming.food.entity.MallProduct;
import com.leiming.food.entity.param.ProductListParam;
import com.leiming.food.entity.param.ProductUpdateParam;

/**
 * 商品表(MallProduct)表服务接口
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:00
 */
public interface MallProductService extends IService<MallProduct> {

    /**
     * 更新商品
     * @param param 更新商品实体
     */
    void updateProduct(ProductUpdateParam param);

    /**
     * 根据id删除商品
     * @param id 商品id
     */
    void deleteProduct(Long id);

    /**
     * 批量上下架
     * @param ids id
     * @param status 状态
     */
    void batchUpdateStatus(Long[] ids, Integer status);

    /**
     * 查询所有分类，可带条件
     * @param param
     * @return
     */
    Page<MallProduct> getList(ProductListParam param);

}

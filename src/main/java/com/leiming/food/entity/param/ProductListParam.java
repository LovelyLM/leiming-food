package com.leiming.food.entity.param;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * @author LovelyLM
 * @date 2021-03-22 23:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProductListParam {

    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品详情
     */
    private String detail;
    /**
     * 分类id
     */

    private Integer categoryId;

    /**
     * 商品上架状态：0-下架，1-上架
     */
    private Integer status;

}

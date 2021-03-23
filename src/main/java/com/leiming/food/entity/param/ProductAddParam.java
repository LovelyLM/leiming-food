package com.leiming.food.entity.param;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * @author LovelyLM
 * @date 2021-03-22 23:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProductAddParam {

    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 产品图片,相对路径地址
     */
    private String image;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 分类id
     */

    private Integer categoryId;
    /**
     * 价格,单位-分
     */
    @Min(value = 1, message = "价格不能小于1分")
    private Integer price;
    /**
     * 库存数量
     */
    @Max(value = 10000,message = "库存不能大于1w")
    @Min(value = 1,message = "库存不能小于1")
    private Integer stock;
    /**
     * 商品上架状态：0-下架，1-上架
     */
    private Integer status;

}

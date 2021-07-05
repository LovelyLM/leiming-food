package com.leiming.food.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LovelyLM
 * @date 2021-03-25 22:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CartVo {

    /**
     * 购物车id
     */
    private Long id;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 是否已勾选：0代表未勾选，1代表已勾选
     */
    private Integer selected;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品价格
     */
    private BigDecimal totalPrice;

    private String productName;

    private String productImage;

}

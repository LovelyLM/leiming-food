package com.leiming.food.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author LovelyLM
 * @date 2021-04-01 22:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class OrderItemVo {

    /**
     * 归属订单id
     */
    private String orderNo;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片
     */
    private String productImg;
    /**
     * 单价（下单时的快照）
     */
    private BigDecimal unitPrice;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 商品总价
     */
    private BigDecimal totalPrice;

}

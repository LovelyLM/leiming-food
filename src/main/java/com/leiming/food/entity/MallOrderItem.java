package com.leiming.food.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 订单的商品表 (MallOrderItem)实体类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MallOrderItem {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
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
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}

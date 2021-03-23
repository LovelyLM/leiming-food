package com.leiming.food.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * 订单表;(MallOrder)实体类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:58
 */
@Data
public class MallOrder {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单号（非主键id）
     */
    private String orderNo;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 订单总价格
     */
    private Integer totalPrice;
    /**
     * 收货人姓名快照
     */
    private String receiverName;
    /**
     * 收货人手机号快照
     */
    private String receiverMobile;
    /**
     * 收货地址快照
     */
    private String receiverAddress;
    /**
     * 订单状态: 0用户已取消，10未付款（初始状态），20已付款，30已发货，40交易完成
     */
    private Integer orderStatus;
    /**
     * 运费，默认为0
     */
    private Integer postage;
    /**
     * 支付类型,1-在线支付
     */
    private Integer paymentType;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 交易完成时间
     */
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}

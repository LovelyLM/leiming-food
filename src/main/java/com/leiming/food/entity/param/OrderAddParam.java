package com.leiming.food.entity.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author LovelyLM
 * @date 2021-03-21 15:26
 */
@Data
public class OrderAddParam {

    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人手机号
     */
    @Length(max = 11, min = 11, message = "手机号非法！")
    private String receiverMobile;
    /**
     * 收货地址
     */
    private String receiverAddress;
    /**
     * 订单状态: 0用户已取消，10未付款（初始状态），20已付款，30已发货，40交易完成
     */
    private Integer orderStatus;
    /**
     * 运费，默认为0
     */
    private Integer postage = 0;
    /**
     * 支付类型,1-在线支付
     */
    private Integer paymentType = 1;
    /**
     * 创建时间
     */
    private Date createTime;
}

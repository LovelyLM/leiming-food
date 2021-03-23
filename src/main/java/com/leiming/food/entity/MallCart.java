package com.leiming.food.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 购物车(MallCart)实体类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class MallCart {

    /**
     * 购物车id
     */
    @TableId(type = IdType.AUTO)
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

}

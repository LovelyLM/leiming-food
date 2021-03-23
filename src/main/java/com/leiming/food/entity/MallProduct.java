package com.leiming.food.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;


/**
 * 商品表(MallProduct)实体类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:00
 */
@Data
public class MallProduct {

    /**
     * 商品主键id
     */
    @TableId(type = IdType.AUTO)
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
    private Long categoryId;
    /**
     * 价格,单位-分
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer stock;
    /**
     * 商品上架状态：0-下架，1-上架
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}

package com.leiming.food.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * 用户表 (MallUser)实体类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("mall_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;
    /**
     * 用户密码，MD5加密
     */


    @Size(min = 8, max = 50, message = "密码长度应在8——50位")
    @NotEmpty(message = "密码不能为空")
    private String password;
    /**
     * 个性签名
     */
    @Size( max = 30, message = "个性签名最多30个字")
    private String personalizedSignature;
    /**
     * 角色，1-普通用户，2-管理员
     */
    private Integer role;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * Md5加盐
     */
    private String salt;

}

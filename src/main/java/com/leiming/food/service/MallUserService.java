package com.leiming.food.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leiming.food.entity.User;

/**
 * 用户表 (MallUser)表服务接口
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:01
 */
public interface MallUserService extends IService<User> {

    /**
     * 用户注册方法
     * @param user 用户实体
     */
    void register(User user);

    /**
     * 登录
     * @param user 用户类
     * @return 登录用户对象
     */
    User login(User user);
}

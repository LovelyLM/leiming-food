package com.leiming.food.controller;




import cn.hutool.core.util.ObjectUtil;

import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.common.Constant;
import com.leiming.food.common.ResultCode;
import com.leiming.food.entity.User;
import com.leiming.food.exception.MallException;
import com.leiming.food.service.MallUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;



/**
 * 用户表 (MallUser)表控制层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:01
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private MallUserService mallUserService;


    /**
     * 用户注册
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping("register")
    public ApiRestResponse register(@Valid @RequestBody User user) {
        mallUserService.register(user);
        return ApiRestResponse.success();
    }

    /**
     * 用户登录
     * @param user 用户实体
     * @param session session
     * @return 统一返回值
     */
    @GetMapping("login")
    public ApiRestResponse login(@Valid User user, HttpSession session) {
        User loginUser = mallUserService.login(user);
        user.setPassword("");
        session.setAttribute(Constant.LOGIN_USER, loginUser);
        return ApiRestResponse.success();
    }


    @PostMapping("updateSign")
    public ApiRestResponse update(String personalizedSignature, HttpSession session) {
        log.info(personalizedSignature);
        User loginUser = (User) session.getAttribute(Constant.LOGIN_USER);
        log.info(String.valueOf(loginUser));
        if (ObjectUtil.isEmpty(loginUser)){
            throw new MallException(ResultCode.USER_NOT_LOGGED_IN);
        }
        mallUserService.updateById(loginUser.setPersonalizedSignature(personalizedSignature));
        return ApiRestResponse.success();
    }

    @GetMapping("loginOut")
    public ApiRestResponse loginOut(HttpSession session) {
        User loginUser = (User) session.getAttribute(Constant.LOGIN_USER);
        log.info(String.valueOf(loginUser));
        if (ObjectUtil.isEmpty(loginUser)){
            throw new MallException(ResultCode.USER_NOT_LOGGED_IN);
        }
        session.removeAttribute(Constant.LOGIN_USER);
        return ApiRestResponse.success();
    }



}

package com.leiming.food.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leiming.food.common.ResultCode;
import com.leiming.food.dao.MallUserDao;
import com.leiming.food.entity.User;
import com.leiming.food.exception.MallException;
import com.leiming.food.service.MallUserService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表 (MallUser)表服务实现类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:54:01
 */
@Service("mallUserService")
public class MallUserServiceImpl extends ServiceImpl<MallUserDao, User> implements MallUserService {

    @Resource
    private MallUserDao userDao;

    @Override
    public void register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        if(ObjectUtil.isEmpty(userDao.selectOne(queryWrapper))){
            String salt = RandomUtil.randomString(10);
            userDao.insert(user.setPassword(DigestUtil.md5Hex(user.getPassword() + salt)).setSalt(salt));
        } else {
            throw new MallException(ResultCode.USER_HAS_EXISTED);
        }
    }

    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User selectUser = userDao.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(selectUser) || StrUtil.equals(DigestUtil.md5Hex(user.getPassword() + selectUser.getSalt()), selectUser.getPassword())){
            throw new MallException(ResultCode.USER_LOGIN_ERROR);
        }
        return selectUser;
    }
}

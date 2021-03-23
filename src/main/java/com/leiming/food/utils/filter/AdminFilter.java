package com.leiming.food.utils.filter;

import cn.hutool.core.util.ObjectUtil;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.common.Constant;
import com.leiming.food.common.ResultCode;
import com.leiming.food.entity.User;
import com.leiming.food.exception.MallException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author LovelyLM
 * @date 2021-03-21 17:27
 */
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        if (ObjectUtil.isEmpty(user)){
            throw new MallException(ResultCode.USER_NOT_LOGGED_IN);
        } else if (!user.getRole().equals(Constant.ADMIN_ROLE)){
            throw new MallException(ResultCode.PERMISSION_NO_ACCESS);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

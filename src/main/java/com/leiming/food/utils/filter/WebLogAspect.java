package com.leiming.food.utils.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.RequestHandledEvent;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 打印请求和拦截信息
 * @author LovelyLM
 * @date 2021-03-08 20:05
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {
    @Pointcut("execution(public * com.leiming.food.controller.*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //收到请求，记录请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //请求时
        log.info("******************************");
        log.info("\n");
        log.info("URL:" +request.getRequestURI().toString());
        log.info("方法：" + request.getMethod());
        log.info("IP地址：" + request.getRemoteAddr());
        log.info(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("参数：" + Arrays.toString(joinPoint.getArgs()));
        log.info("\n");

        log.info("******************************");
    }

    @AfterReturning(returning = "res", pointcut = "webLog()")
    public void doAfter(Object res) throws JsonProcessingException {
        log.info("\n");
        log.info("\n");
        log.info("******************************");
        log.info("响应数据：" + new ObjectMapper().writeValueAsString(res));
        log.info("******************************");
        log.info("\n");
        log.info("\n");


    }
}

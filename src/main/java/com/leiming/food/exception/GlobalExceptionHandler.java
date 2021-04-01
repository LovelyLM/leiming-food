package com.leiming.food.exception;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.common.ResultBuilder;
import com.leiming.food.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

/**
 * 描述：     处理统一异常的handler
 * @author 10796
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error("Default Exception: ", e);
        return ResultBuilder.fail(ResultCode.SYSTEM_INNER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException: ", e);
        return ResultBuilder.fail(ResultCode.INTERFACE_METHOD_NOT_SPORT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException: ", e);
        return ResultBuilder.fail(ResultCode.INTERFACE_JSON_ERROR);
    }



    @ExceptionHandler(MallException.class)
    public Object handleImoocMallException(MallException e) {
        log.error("MallException: ", e);
        return ResultBuilder.error(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(BindException.class)
    public ApiRestResponse handleBindException(BindException e) {
        log.error("BindException: ", e);
        return handleBindingResult(e.getBindingResult());
    }

    @ExceptionHandler(ValidationException.class)
    public Object validationException(ValidationException e) {
        String msg = StrUtil.sub(e.getLocalizedMessage(), e.getLocalizedMessage().indexOf(":") + 1, e.getLocalizedMessage().length() + 1);
        log.error("ValidationException: ", e);
        return ApiRestResponse.error(500, msg.trim());

    }

    private ApiRestResponse handleBindingResult(BindingResult result) {
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        return ApiRestResponse.error(500, String.valueOf(list));
    }
}

package com.leiming.food.exception;

import com.leiming.food.common.ResultCode;

/**
 * 描述：     统一异常
 * @author 10796
 */
public class MallException extends RuntimeException {

    private final Integer code;
    private final String message;

    public MallException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MallException(ResultCode exceptionCode) {
        this(exceptionCode.getCode(), exceptionCode.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

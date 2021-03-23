package com.leiming.food.common;

/**
 * @author LovelyLM
 */
public class ResultBuilder {


    /**
     * 成功无数据
     * @param code
     * @param <T>
     * @return
     */
    public static <T> Response<T> successNoData(ResultCode code){
        Response<T> result = new Response<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }

    /**
     * 成功
     * @param t
     * @param code
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T t, ResultCode code){
        Response<T> result = new Response<T>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setData(t);
        return result;
    }

    /**
     * 失败
     * @param code
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(ResultCode code){
        Response<T> result = new Response<>();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        return result;
    }

    /**
     * 异常
     * @param code
     * @param <T>
     * @return
     */
    public static <T> Response<T> error(Integer code, String msg){
        Response<T> result = new Response<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}

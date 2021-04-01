package com.leiming.food.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author LovelyLM
 * @date 2021-03-18 23:07
 */


public class Constant {
    public static final String LOGIN_USER = "loginUser";
    public static final Integer ADMIN_ROLE = 2;

    public static final String PRODUCT_TABLE_NAME = "name";
    public static final Integer PRODUCT_UP = 1;
    public static final Integer PRODUCT_DOWN = 0;
    public static final Integer STATUS_0 = 0;
    public static final Integer STATUS_1 = 1;
    public static final Integer ORDER_CANCEL = 0;
    public static final Integer ORDER_NOT_PAY = 1;
    public static final Integer ORDER_PAID = 2;
    public static final Integer ORDER_DELIVERED = 3;
    public static final Integer ORDER_FINISHED = 4;

    public static final String FILE_UPLOAD_DIR = "E:\\mypersonnel\\IdeaProject\\food\\src\\main\\resources\\static\\upload\\";
}

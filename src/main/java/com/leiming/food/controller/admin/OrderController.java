package com.leiming.food.controller.admin;


import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.entity.MallOrder;
import com.leiming.food.entity.param.OrderAddParam;
import com.leiming.food.service.MallOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;

/**
 * 订单表;(MallOrder)表控制层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:59
 */
@RestController
@RequestMapping("admin/order")
@Validated
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private MallOrderService mallOrderService;


    /**
     * 获取订单列表
     * @param page
     * @return
     */
    @GetMapping("getOrderList")
    public ApiRestResponse getOrderList(Page<MallOrder> page){
        return ApiRestResponse.success(mallOrderService.getAllOrderDetailList(page));
    }

    /**
     * 订单发货
     * @param orderNo
     * @return
     */
    @PostMapping("delivered")

    public ApiRestResponse delivered(@NotEmpty(message = "订单No不能为空") String orderNo){

        mallOrderService.delivered(orderNo);
        return ApiRestResponse.success();
    }

    /**
     * 订单完成
     * @param orderNo
     * @return
     */
    @PostMapping("finished")

    public ApiRestResponse finished(@NotEmpty(message = "订单No不能为空") String orderNo){
        mallOrderService.finished(orderNo);
        return ApiRestResponse.success();
    }


}

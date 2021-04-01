package com.leiming.food.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.entity.MallOrder;
import com.leiming.food.entity.param.OrderAddParam;
import com.leiming.food.service.MallOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 订单表;(MallOrder)表控制层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:59
 */
@RestController
@RequestMapping("order")
@Validated
public class MallOrderController {
    /**
     * 服务对象
     */
    @Resource
    private MallOrderService mallOrderService;


    /**
     * 创建订单
     * @param param 订单创建实体
     * @return 返回值
     */
    @PostMapping("createOrder")
    public ApiRestResponse createOrder(@RequestBody @Valid OrderAddParam param) {
        mallOrderService.createOder(param);
        return ApiRestResponse.success();
    }

    @GetMapping("getOrderDetail")
    public ApiRestResponse getOrderDetail(@NotEmpty(message = "订单No.不能为空") String orderNo){
        return ApiRestResponse.success(mallOrderService.getOrderDetail(orderNo));
    }

    @GetMapping("getOrderList")
    public ApiRestResponse getOrderList(Page<MallOrder> page){
        return ApiRestResponse.success(mallOrderService.getOrderDetailList(page));
    }
}

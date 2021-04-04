package com.leiming.food.controller;



import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.common.ResultCode;
import com.leiming.food.entity.MallOrder;
import com.leiming.food.entity.param.OrderAddParam;
import com.leiming.food.exception.MallException;
import com.leiming.food.service.MallOrderService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("order")
@Validated
@Slf4j
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

    /**
     * 获取订单详情
     * @param orderNo
     * @return
     */
    @GetMapping("getOrderDetail")
    public ApiRestResponse getOrderDetail(@NotEmpty(message = "订单No.不能为空") String orderNo){
        return ApiRestResponse.success(mallOrderService.getOrderDetail(orderNo));
    }

    /**
     * 获取订单列表
     * @param page
     * @return
     */
    @GetMapping("getOrderList")
    public ApiRestResponse getOrderList(Page<MallOrder> page){
        return ApiRestResponse.success(mallOrderService.getOrderDetailList(page));
    }

    /**
     * 取消订单
     * @param orderNo
     * @return
     */
    @PostMapping("cancel")
    public ApiRestResponse cancel(@NotEmpty(message = "订单No.不能为空") String orderNo){
        mallOrderService.orderCancel(orderNo);
        return ApiRestResponse.success();
    }

    /**
     * 获取支付二维码
     * @param request
     * @param response
     * @param orderNo
     * @throws IOException
     */
    @GetMapping("grCode")
    public void grCode(HttpServletRequest request, HttpServletResponse response, @NotEmpty(message = "订单No不能为空") String orderNo) throws IOException {
        int port = request.getLocalPort();
        String randomNumbers = RandomUtil.randomNumbers(10);

        request.getSession().setAttribute("random", randomNumbers);
        String random = "&random=" + randomNumbers;
        ServletOutputStream outputStream = response.getOutputStream();
        QrCodeUtil.generate("http://127.0.0.1:" + port + "/" + "order/pay?orderNo=" + orderNo + random, 300, 300, ImgUtil.IMAGE_TYPE_PNG,outputStream);
    }

    /**
     * 支付
     * @param orderNo
     * @param request
     * @param random
     * @return
     */
    @GetMapping("pay")
    public ApiRestResponse grCode(@NotEmpty(message = "订单No不能为空") String orderNo, HttpServletRequest request ,@NotEmpty(message = "支付链接无效") String random) {
        String attribute = (String) request.getSession().getAttribute("random");
        if (ObjectUtil.isEmpty(attribute) || ObjectUtil.notEqual(random, attribute)){
            throw new MallException(ResultCode.QR_INVALIDATION);
        }
        mallOrderService.pay(orderNo);
        return ApiRestResponse.success();
    }

    /**
     * 完成订单
     * @param orderNo
     * @return
     */
    @PostMapping("finished")

    public ApiRestResponse finished(@NotEmpty(message = "订单No不能为空") String orderNo){
        mallOrderService.finished(orderNo);
        return ApiRestResponse.success();
    }
}

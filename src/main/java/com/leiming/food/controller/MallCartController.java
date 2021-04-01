package com.leiming.food.controller;



import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.service.MallCartService;
import com.leiming.food.utils.filter.UserFilter;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 购物车(MallCart)表控制层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:56
 */
@RestController
@RequestMapping("cart")
public class MallCartController {
    /**
     * 服务对象
     */
    @Resource
    private MallCartService mallCartService;


    @PostMapping("add")
    public ApiRestResponse add(@RequestParam Long productId, @RequestParam Integer count ){

        mallCartService.add(UserFilter.currentUser.getId(), productId, count) ;
        return ApiRestResponse.success();
    }

    @GetMapping("getCarList")
    public ApiRestResponse getCarList(){
        Long userId = UserFilter.currentUser.getId();
        return ApiRestResponse.success(mallCartService.getCarList(userId));
    }


    @PostMapping("deleteCart")
    public ApiRestResponse deleteCart(Long productId){

        Long userId = UserFilter.currentUser.getId();
        mallCartService.deleteCart(userId,productId);
        return ApiRestResponse.success();
    }

    @PostMapping("selectCart")
    public ApiRestResponse selectCart(@RequestParam Long productId, Integer select){
        Long userId = UserFilter.currentUser.getId();
        return ApiRestResponse.success(mallCartService.selectOrNot(userId, productId, select));
    }

    @PostMapping("selectAllCart")
    public ApiRestResponse selectAllCart(@RequestParam Integer select){
        Long userId = UserFilter.currentUser.getId();
        return ApiRestResponse.success(mallCartService.selectAllOrNot(userId, select));
    }




}

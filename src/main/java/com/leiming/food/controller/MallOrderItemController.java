package com.leiming.food.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leiming.food.entity.MallOrderItem;
import com.leiming.food.service.MallOrderItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 订单的商品表 (MallOrderItem)表控制层
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:59
 */
@RestController
@RequestMapping("mallOrderItem")
public class MallOrderItemController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private MallOrderItemService mallOrderItemService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param mallOrderItem 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<MallOrderItem> page, MallOrderItem mallOrderItem) {
        return success(this.mallOrderItemService.page(page, new QueryWrapper<>(mallOrderItem)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.mallOrderItemService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param mallOrderItem 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody MallOrderItem mallOrderItem) {
        return success(this.mallOrderItemService.save(mallOrderItem));
    }

    /**
     * 修改数据
     *
     * @param mallOrderItem 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody MallOrderItem mallOrderItem) {
        return success(this.mallOrderItemService.updateById(mallOrderItem));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.mallOrderItemService.removeByIds(idList));
    }
}
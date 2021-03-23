package com.leiming.food.entity.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author LovelyLM
 * @date 2021-03-21 15:26
 */
@Data
public class CategoryPageParam extends Page {
    /**
     * 分类id
     */
    private Long id;
    /**
     * 分类目录名称
     */
    @NotBlank(message = "分类目录名称不能为空")
    private String name;
    /**
     * 分类目录级别，例如1代表一级，2代表二级，3代表三级
     */
    @NotNull(message = "分类目录级别不能为空")
    private Integer type;
    /**
     * 父id，也就是上一级目录的id，如果是一级目录，那么父id为0
     */
    @NotNull(message = "分类父id不能为空")
    private Integer parentId;
    /**
     * 目录展示时的排序
     */
    private Integer orderNum = 1;

}

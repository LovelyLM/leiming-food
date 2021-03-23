package com.leiming.food.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 商品分类 (MallCategory)实体类
 *
 * @author LovelyLM
 * @since 2021-03-08 19:53:57
 */
@Data

public class CategoryVo implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 分类目录名称
     */
    private String name;
    /**
     * 分类目录级别，例如1代表一级，2代表二级，3代表三级
     */
    private Integer type;
    /**
     * 父id，也就是上一级目录的id，如果是一级目录，那么父id为0
     */
    private Integer parentId;

    private List<CategoryVo> children = new ArrayList<>();

}

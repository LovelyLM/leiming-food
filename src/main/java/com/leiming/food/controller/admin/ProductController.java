package com.leiming.food.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leiming.food.common.ApiRestResponse;
import com.leiming.food.common.Constant;
import com.leiming.food.common.ResultCode;
import com.leiming.food.entity.MallProduct;
import com.leiming.food.entity.param.ProductAddParam;
import com.leiming.food.entity.param.ProductUpdateParam;
import com.leiming.food.entity.vo.CategoryVo;
import com.leiming.food.exception.MallException;
import com.leiming.food.service.MallProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LovelyLM
 * @date 2021-03-22 23:19
 */

@RestController(value = "AdminProductController")
@RequestMapping("admin/product")
public class ProductController {
    @Resource
    private MallProductService productService;


    /**
     * 添加商品
     * @param addParam 添加商品实体类
     * @return 统一返回对象
     */
    @PostMapping("add")
    public ApiRestResponse addProduct(@RequestBody @Valid ProductAddParam addParam){
        QueryWrapper<MallProduct> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(productService.getOne(queryWrapper.eq(Constant.PRODUCT_TABLE_NAME, addParam.getName())))){
            throw new MallException(ResultCode.PRODUCT_NAME_REPEAT);
        }
        MallProduct product = new MallProduct();
        BeanUtil.copyProperties(addParam, product);
        productService.save(product);
        return ApiRestResponse.success();

    }

    /**
     * 更新商品
     * @param param 更新商品实体类
     * @return 统一返回对象
     */
    @PostMapping("update")
    public ApiRestResponse updateProduct(@RequestBody @Valid ProductUpdateParam param){
        productService.updateProduct(param);
        return ApiRestResponse.success();
    }

    /**
     * 删除商品
     * @param id 商品id
     * @return 统一返回对象
     */
    @PostMapping("delete")
    public ApiRestResponse deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
        return ApiRestResponse.success();
    }

    /**
     * 批量更新商品上下架
     * @param ids
     * @param status
     * @return
     */
    @PostMapping("update_status_batch")
    public ApiRestResponse updateStatusBatch(@RequestParam Long[] ids, Integer status){
        productService.batchUpdateStatus(ids, status);
        return ApiRestResponse.success();

    }


    @GetMapping("find_page_product")
    public ApiRestResponse findCategoryPage( Page<MallProduct> page, MallProduct product){
        Page<MallProduct> productPage = productService.page(page);
        return ApiRestResponse.success(productPage);
    }



    /**
     * 图片上传
     * @param file
     * @param request
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    @PostMapping("upload")
    public ApiRestResponse uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException, URISyntaxException {

        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String fileExtName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = IdUtil.simpleUUID();
        String fileName = uuid  + fileExtName;
        File fileDir = new File(Constant.FILE_UPLOAD_DIR);
        File destFile = new File(Constant.FILE_UPLOAD_DIR + fileName);
        if (!fileDir.exists()){
            if (fileDir.mkdir()){
                throw new MallException(ResultCode.SYSTEM_INNER_ERROR);
            }
        }
        file.transferTo(destFile);
        return ApiRestResponse.success(getUri(new URI(request.getRequestURL().toString())) + "/upload/" + fileName);

    }


    /**
     * 获取url
     * @param uri
     * @return
     * @throws URISyntaxException
     */
    private URI getUri(URI uri) throws URISyntaxException {
        URI newUri;
        newUri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        return newUri;
    }
}

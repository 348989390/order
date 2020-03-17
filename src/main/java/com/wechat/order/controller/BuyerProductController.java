package com.wechat.order.controller;

import com.wechat.order.VO.ProductInfoVO;
import com.wechat.order.VO.ProductVO;
import com.wechat.order.VO.ResultVO;
import com.wechat.order.dataobject.ProductCategory;
import com.wechat.order.dataobject.ProductInfo;
import com.wechat.order.service.CategoryService;
import com.wechat.order.service.ProductInfoService;
import com.wechat.order.util.ResultVOUtil;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
@Log
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfolist =  productInfoService.findUpAll();
        log.info("上架商品:"+productInfolist.toString());
        //2.查询类目
        List<Integer> categoryTypelist = new ArrayList<>();
        for(ProductInfo info:productInfolist){
            categoryTypelist.add(info.getCategoryType());
        }
        List<ProductCategory> catelist  =  categoryService.findByCategoryTypeId(categoryTypelist);
        log.info("类目:"+catelist.size());
        log.info(catelist.toString());

        List<ProductVO> productvolist  = new ArrayList<>();
        for(ProductCategory category:catelist){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(category.getCategoryType());
            productVO.setCategoryName(category.getCategoryName());
            List<ProductInfoVO> productInfoList = new ArrayList<>();
            for(ProductInfo info:productInfolist){
                if(info.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(info,productInfoVO);
                    productInfoList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoList);
            productvolist.add(productVO);
        }

        //3.数据拼装
        /*ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(productvolist);*/
        return ResultVOUtil.success(productvolist);
    }
}

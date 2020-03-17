package com.wechat.order.service.impl;

import com.wechat.order.dataobject.ProductCategory;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Transactional
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void findone() {
        ProductCategory category = categoryService.findone(1);
        log.info(category.toString());
    }

    @Test
    void findAll() {
        List<ProductCategory> list = categoryService.findAll();
        for(ProductCategory temp:list){
            log.info("==="+temp.toString());
        }
    }

    @Test
    void findByCategoryTypeId() {
        List<Integer> int_list = new ArrayList<Integer>();
        int_list.add(1);
        int_list.add(2);
        List<ProductCategory> list =categoryService.findByCategoryTypeId(int_list);
        for(ProductCategory temp:list){
            log.info("==="+temp.toString());
        }
    }

    @Test
    void save() {
        ProductCategory category =new ProductCategory();
        category.setCategoryName("haha ");
        category.setCategoryType(4);
        categoryService.save(category);
    }
}
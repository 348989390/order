package com.wechat.order.service.impl;

import com.wechat.order.dao.ProductCategoryDao;
import com.wechat.order.dataobject.ProductCategory;
import com.wechat.order.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao categoryDao;

    @Override
    public ProductCategory findone(Integer categoryId) {
        ProductCategory category = new ProductCategory();
        return categoryDao.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeId(List<Integer> categoryTypeList) {
        return categoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public void save(ProductCategory category) {
        categoryDao.save(category);
    }
}

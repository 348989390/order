package com.wechat.order.service;

import com.wechat.order.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findone(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory>   findByCategoryTypeId(List<Integer> categoryTypeList);

    void save(ProductCategory category);
}

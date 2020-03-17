package com.wechat.order.dao;

import com.wechat.order.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus (Integer productStatus);

    ProductInfo findOneByProductId(String productId);
}

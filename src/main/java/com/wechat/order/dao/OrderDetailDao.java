package com.wechat.order.dao;

import com.wechat.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);

    List<OrderDetail> findByProductId(String productId);

    OrderDetail findByDetailId(String detailId);

    List<OrderDetail> findAll();

}

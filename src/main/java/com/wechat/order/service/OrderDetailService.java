package com.wechat.order.service;

import com.wechat.order.dataobject.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {

    List<OrderDetail> findByOrderId(String orderId);

    List<OrderDetail> findByProductId(String productId);

    OrderDetail findByDetailId(String detailId);

    List<OrderDetail> findAll();
}

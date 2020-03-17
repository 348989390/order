package com.wechat.order.service.impl;

import com.wechat.order.dao.OrderDetailDao;
import com.wechat.order.dataobject.OrderDetail;
import com.wechat.order.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailSeviceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDao dao;


    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
       return dao.findByOrderId(orderId);
    }

    @Override
    public List<OrderDetail> findByProductId(String productId) {
        return dao.findByProductId(productId);
    }

    @Override
    public OrderDetail findByDetailId(String detailId) {
        return dao.findByDetailId(detailId);
    }

    @Override
    public List<OrderDetail> findAll() {
        return dao.findAll();
    }
}

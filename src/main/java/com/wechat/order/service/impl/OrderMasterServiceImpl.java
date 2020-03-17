package com.wechat.order.service.impl;

import com.wechat.order.dao.OrderMasterDao;
import com.wechat.order.dataobject.OrderMaster;
import com.wechat.order.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterDao dao;
    @Override
    public void save(OrderMaster orderMaster) {
        dao.save(orderMaster);
    }

    @Override
    public List<OrderMaster> findAll() {
        return dao.findAll();
    }

    @Override
    public OrderMaster findByOrderId(String orderId) {
        return dao.findByOrderId(orderId);
    }

    @Override
    public Page<OrderMaster> findByOpenId(String openId, Pageable pageable) {
        return dao.findByBuyerOpenid(openId,pageable);
    }
}

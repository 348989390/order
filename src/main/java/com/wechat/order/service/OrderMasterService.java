package com.wechat.order.service;

import com.wechat.order.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderMasterService {

    /** 保存订单*/
    void save(OrderMaster orderMaster);

    /** 查询所有*/
    List<OrderMaster> findAll();

    /** 根据ID查*/
    OrderMaster findByOrderId(String orderId);

    /** 根据微信openId查询*/
    Page<OrderMaster> findByOpenId(String openId, Pageable pageable);
}

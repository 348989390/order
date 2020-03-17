package com.wechat.order.dao;

import com.wechat.order.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.List;

public interface OrderMasterDao extends JpaRepository<OrderMaster, Spring> {

    OrderMaster findByOrderId(String orderId);

    Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);

    List<OrderMaster> findAll();
}

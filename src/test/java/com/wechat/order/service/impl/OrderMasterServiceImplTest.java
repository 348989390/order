package com.wechat.order.service.impl;

import com.wechat.order.dataobject.OrderMaster;
import com.wechat.order.service.OrderMasterService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
class OrderMasterServiceImplTest {
    @Autowired
    private OrderMasterService service;

    @Test
    void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerName("小李");
        orderMaster.setBuyerOpenid("jzw13294097311");
        orderMaster.setBuyerPhone("13294097311");
        orderMaster.setOrder_status(0);
        orderMaster.setOrderAmount(new BigDecimal(99));
        orderMaster.setPayStatus(0);
        orderMaster.setOrderId("2");
        service.save(orderMaster);
    }

    @Test
    void findAll() {
    }

    @Test
    void findByOrderId() {
    }
}
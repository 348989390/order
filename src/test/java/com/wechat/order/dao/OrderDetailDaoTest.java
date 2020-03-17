package com.wechat.order.dao;

import com.wechat.order.dataobject.OrderDetail;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log
class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao dao;

    @Test
    void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567890");
        orderDetail.setOrderId("123123123");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("111111111112");
        orderDetail.setProductName("小米粥");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductQuantity(5);
        dao.save(orderDetail);
    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> list =  dao.findByOrderId("1");
        log.info("长度："+list.size());
        log.info(list.toString());
    };


}
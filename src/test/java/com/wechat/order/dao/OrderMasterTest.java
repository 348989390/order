package com.wechat.order.dao;

import com.wechat.order.dataobject.OrderMaster;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log
class OrderMasterTest {

    @Autowired
    private OrderMasterDao dao;
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerName("小李");
        orderMaster.setBuyerOpenid("jzw13294097311");
        orderMaster.setBuyerPhone("13294097311");
        orderMaster.setOrder_status(0);
        orderMaster.setOrderAmount(new BigDecimal(99));
        orderMaster.setPayStatus(0);
        orderMaster.setOrderId("3");

        dao.save(orderMaster);

    }

    @Test
    public void findByOrderId(){
        OrderMaster orderMaster = dao.findByOrderId("1");
        log.info(orderMaster.toString());
    }

    @Test
    public void findByBuyerOpenId(){
        PageRequest request = PageRequest.of(0,2);
        Page<OrderMaster> orderList = dao.findByBuyerOpenid("jzw13294097311",request);

        System.out.println(orderList.getTotalElements());
        System.out.println(orderList.getContent());
//        for(OrderMaster order:orderList){
//            log.info(order.toString());
//        }
    }

    @Test
    public void findAll(){
        List<OrderMaster>  orderList = dao.findAll();
        for(OrderMaster order:orderList){
            log.info(order.toString());
        }
    };


}
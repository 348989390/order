package com.wechat.order.service.impl;

import com.wechat.order.dataobject.OrderDetail;
import com.wechat.order.dto.OrderDTO;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String buyerOpenId="jzw13294097311";

    @Test
    public void create(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("老纪");
        orderDTO.setBuyerAddress("北京");
        orderDTO.setBuyerPhone("13294097311");
        orderDTO.setBuyerOpenid("jzw13294097311");
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);


        //购物车

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(3);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result{}"+result);
    }

    @Test
    public void findOne(){
        OrderDTO orderDTO = orderService.findOne("1584087271831957782");
        log.info(orderDTO.toString());
    }

    @Test
    public void cancel(){
        OrderDTO orderDTO = orderService.findOne("1584086894274478371");
        orderService.cancel(orderDTO);
    }

    @Test
    public void finish(){
        OrderDTO orderDTO = orderService.findOne("1584086894274478371");
        orderService.finish(orderDTO);
    }

    @Test
    public void paid(){
        OrderDTO orderDTO = orderService.findOne("1584086894274478371");
        orderService.paid(orderDTO);
    }
}
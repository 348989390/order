package com.wechat.order.convent;

import com.wechat.order.dataobject.OrderMaster;
import com.wechat.order.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderMaster,orderDTO);
        return  orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
//        orderMasterList.
        return null;
    }

}

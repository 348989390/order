package com.wechat.order.controller;

import com.wechat.order.VO.ResultVO;
import com.wechat.order.dataobject.OrderMaster;
import com.wechat.order.enums.ResultEnum;
import com.wechat.order.exception.SellException;
import com.wechat.order.form.OrderForm;
import com.wechat.order.service.OrderService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }


        return null;
    }

    //订单列表

    //订单详情

    //取消订单
}

package com.wechat.order.dto;

import com.wechat.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;

    /** 买家名字*/
    private String buyerName;

    /** 买家电话*/
    private String buyerPhone;

    /** 买家地址*/
    private String buyerAddress;

    /** 买家微信openid*/
    private String buyerOpenid;

    /** 订单总金额*/
    private BigDecimal orderAmount;

    /** 订单状态,默认0新下单*/
    private Integer order_status;

    /** 支付状态，默认0未支付*/
    private Integer payStatus;

    /** 创建时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}

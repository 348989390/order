package com.wechat.order.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NO_EXIST(1,"商品不存在"),
    PRODUCT_STOCK_ERROE(2,"商品售罄"),
    ORDER_NOT_EXIST(3,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(4,"订单详情不存在"),
    ORDER_STATUS_ERROR(5,"订单状态不正确"),
    ORDER_UPDATE_ERROR(6,"订单更新失败"),
    ORDER_DETAIL_EMPTY(7,"订单详情为空"),
    ORDER_PAID_ERROR(8,"订单支付状态不正确"),

    PARAM_ERROR(10,"参数不正确"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

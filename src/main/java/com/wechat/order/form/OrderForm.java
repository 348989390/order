package com.wechat.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
    @NotEmpty(message = "姓名必填")
    /** 买家姓名*/
    private String name;

    @NotEmpty(message = "手机号必填")
    /** 买家手机号*/
    private String phone;

    @NotEmpty(message = "地址必填")
    /** 买家地址*/
    private String address;

    @NotEmpty(message = "openid必填")
    /** 买家openid*/
    private String openid;

    @NotEmpty(message = "购物车必填")
    /** 购物车*/
    private String items;

    public OrderForm(){

    }
}

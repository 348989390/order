package com.wechat.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String productId;
    /** 商品名称*/
    private String productName;
    /** 商品价格*/
    private BigDecimal productPrice;
    /** 商品数量*/
    private Integer productQuantity;
    /** 小图*/
    private String productIcon;
    /** 创建时间*/
    private Date createTime;
    /** 更新时间*/
    private Date updateTime;

    public OrderDetail(){

    }
}

package com.wechat.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品
 */
@Entity
@Data
public class ProductInfo {

    @Id
     private String productId;

    /** 商品名称*/
    private String productName;

    /** 商品价格*/
    private BigDecimal productPrice;

    /** 商品库存*/
    private Integer productStock;

    /** 商品描述*/
    private String productDescription;

    /** 商品小图*/
    private String productIcon;

    /** 商品状态 （0.正常1.下架）*/
    private Integer productStatus;

    /** 商品编号*/
    private Integer categoryType;

    public ProductInfo(){

    }
}

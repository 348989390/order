package com.wechat.order.dataobject;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目表
 */
@Entity
@DynamicUpdate//动态更新
@Data//自动生成get、set、tostring方法
public class ProductCategory {
    /** 类目id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private Integer categoryId;
    /** 类目名称*/
    private String categoryName;
    /** 类目编号*/
    private Integer categoryType;
    /** 更新时间*/
    private Date updateTime;
    /** 创建时间*/
    private Date createTime;

    public ProductCategory(){

    }

}

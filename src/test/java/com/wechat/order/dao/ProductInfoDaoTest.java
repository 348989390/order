package com.wechat.order.dao;

import com.wechat.order.dataobject.ProductInfo;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao dao;

    @Test
    public void findByProductStatus(){
        List<ProductInfo> product_list = dao.findByProductStatus(0);
        for(ProductInfo temp:product_list){
            log.info(temp.toString());
        }
//        return product_list;
    }

    @Test
    public void saveTest(){
        ProductInfo info = new ProductInfo();
        info.setCategoryType(2);
        info.setProductDescription("好");
        info.setProductIcon("asdjioajdioasjd");
        info.setProductName("商品一");
        info.setProductPrice(new BigDecimal(11));
        info.setProductStatus(0);
        info.setProductStock(99);
        info.setProductId("2");
        dao.save(info);

    }

    @Test
    public void findOneByProductId(){
        ProductInfo productInfo = dao.findOneByProductId("1");
        log.info(productInfo.toString());
    }

}
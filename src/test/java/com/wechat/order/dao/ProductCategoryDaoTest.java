package com.wechat.order.dao;

import com.wechat.order.dataobject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCategoryDaoTest {

    private Logger log = LoggerFactory.getLogger(ProductCategoryDaoTest.class);

    @Autowired
    private ProductCategoryDao dao;

    @Test
    public void findTest(){
        Optional<ProductCategory> category = dao.findById(1);
        System.out.println(category.toString());
    }

    @Test
    @Transactional//测试数据提交后立马回滚，不会造成数据库里保存很多测试数据的问题。
    public void saveTest(){
        ProductCategory  category = new ProductCategory();
//        ProductCategory  category = dao.findById(1).get();
//        category.setCategoryId(1);
        category.setCategoryName("保存");
        category.setCategoryType(1);
        dao.save(category);

    }

    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list  = Arrays.asList(1,2,3,4);
        List<ProductCategory> result = dao.findByCategoryTypeIn(list);
        log.info(result.toString());

    }
}
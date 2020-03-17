package com.wechat.order;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class LoggerTest {
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

@Test
    public void test1(){
        logger.info("info");
        logger.error("error");
        logger.debug("debug");
        String name = "name";
        logger.info("name: {},password: {}",name,name);
    }
}

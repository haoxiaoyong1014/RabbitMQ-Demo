package com.hxy.test;

import com.hxy.RabbitMQ.HelloApplication;
import com.hxy.RabbitMQ.springBootRabbitMQ.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/12/7.
 * hxy
 * Demo3 springBoot整合RabbitMQ
 * <p>
 * 创建单元测试类，用来调用消息生产：
 */
@MapperScan(basePackages = "com.hxy")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class HelloApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    public void hello() throws Exception {
        sender.send();
    }
}

package com.hxy.RabbitMQ.springBootRabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/7.
 * com.hxy.RabbitMQ.hxy
 * Demo3 springBoot整合RabbitMQ
 *
 * 创建消息生产者Sender。通过注入AmqpTemplate接口的实例来实现消息的发送，
 * AmqpTemplate接口定义了一套针对AMQP协议的基础操作。
 * 在Spring Boot中会根据配置来注入其具体实现。
 * 在该生产者，我们会产生一个字符串，并发送到名为hello的队列中。
 */
@Component
public class Sender {
    private final static String context="hello "+ new Date();

    private final static String mage="HelloWorld";

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){



        System.out.println("Sender : "+context);

        rabbitTemplate.convertAndSend("hello",context);
        rabbitTemplate.convertAndSend("hello2",mage);
    }
}

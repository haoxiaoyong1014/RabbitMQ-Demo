package com.hxy.RabbitMQ.msgConfirm;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/12/8.
 * hxy
 * 测试消息确定机制
 *
 * 如下代码，首先设置 需要使用 确认模式
 * （Ps：注意，后面还要设置 rabbitTemplate.setMandatory(true);，
 * 不然connectionFactory.setPublisherReturns(true); 是不会起作用的，见后面代码）
 */
@Configuration
public class ConfirmConfig {

    @Bean
    public ConnectionFactory createConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1", 5672);
        connectionFactory.setUsername("hxy");
        connectionFactory.setPassword("hxy1");
        connectionFactory.setVirtualHost("/");

        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirms(true);                               //开启消息确认机制
        //Connection conn = connectionFactory.createConnection();
        //Channel channel = conn.createChannel(true);
        //channel.queueDeclare("testPublishModeQueue1",false,false,false,null);

        return connectionFactory;
    }

    /**
     * 定义回调，如下，实现了 RabbitTemplate.ConfirmCallback 和 RabbitTemplate.ReturnCallback
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true);                                           //设置 setMandatory(true);不然connectionFactory.setPublisherReturns(true); 是不会起作用的

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback()
        {     //ConfirmCallback方法
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("================");
                System.out.println("correlationData = " + correlationData);
                System.out.println("ack = " + ack);
                System.out.println("cause = " + cause);
                System.out.println("================");
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {      //ReturnCallback方法
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("================");
                System.out.println("message = " + message);
                System.out.println("replyCode = " + replyCode);
                System.out.println("replyText = " + replyText);
                System.out.println("exchange = " + exchange);
                System.out.println("routingKey = " + routingKey);
                System.out.println("================");
            }
        });

        return rabbitTemplate;
    }





}

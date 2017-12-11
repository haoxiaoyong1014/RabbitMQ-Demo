package com.hxy.RabbitMQ.springBootRabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/7.
 * com.hxy.RabbitMQ.hxy
 * Demo3 springBoot整合RabbitMQ
 *
 * 创建RabbitMQ的配置类RabbitConfig，用来配置队列、交换器、路由等高级信息。
 * 这里我们以入门为主，先以最小化的配置来定义，以完成一个基本的生产和消费过程。
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){

        return new Queue("hello");                      //创建队列
    }

    @Bean
    public Queue helloQueue2(){

        return new Queue("hello2");                      //创建队列
    }

    /*@Bean
    public Exchange hello(){

        return new FanoutExchange("hello");             //创建交换机
    }*/

    @Bean
    public Exchange hello2(){

        return new DirectExchange("hello2");
    }

    @Bean
    public  Binding GetBinding(){                       //创建绑定
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("hello","hello");

       return new Binding("hello",Binding.DestinationType.EXCHANGE,"hello2","hello",map);//目的地,目的地的类型,.交换机,路由键,map
    }
    //已经在配置文件中配置
    @Bean
    @ConfigurationProperties(prefix = "hxy.rabbitmq")
    public ConnectionFactory createConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1", 5672);
        connectionFactory.setUsername("hxy");
        connectionFactory.setPassword("hxy1");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirms(true);     //开启消息确认机制

        return connectionFactory;
    }
    /*@Bean
    @ConfigurationProperties(prefix = "hxy.rabbitmq")
    public Config createConfig(){
        Config config=new Config();

        //config.setPort(5672);
        // config.setUsername("hxy");
        //config.setPassword("hxy1");
        //config.setHost("localhost");
        return config;
    }*/

}

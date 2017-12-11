package com.hxy.RabbitMQ.hxy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Demo2
 * Created by Administrator on 2017/12/7.
 * com.hxy.RabbitMQ.hxy
 */
public class BaseConnector {

    protected Channel channel;                                                                 //通道

    protected Connection connection;                                                           //连接

    protected String queueName;                                                                //队列名称


    public BaseConnector(String queueName) throws IOException, TimeoutException {

        this.queueName=queueName;

        ConnectionFactory factory=new ConnectionFactory();                                     //打开通道连接

        factory.setHost("127.0.0.1");                                                          //设置MabbitMQ所在的主机ip或者主机名

        connection=factory.newConnection();                                                    //创建连接

        channel=connection.createChannel();                                                     //创建频道

        channel.queueDeclare(queueName,false,false,false,null);                                 //声明创建队列


    }

}

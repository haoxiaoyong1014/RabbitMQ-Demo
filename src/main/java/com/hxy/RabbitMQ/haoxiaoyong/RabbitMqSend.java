package com.hxy.RabbitMQ.haoxiaoyong;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Demo1
 * Created by Administrator on 2017/12/6.
 * com.hxy.RabbitMQ.hxy
 */
public class RabbitMqSend {

    private final static String Queue_Name="queue";               //队列的名字

    public static void main(String[] args) throws IOException, TimeoutException {
        /**
         * 创建连接连接到MabbitMQ发送方
         */
        ConnectionFactory factory=new ConnectionFactory();            //创建连接对象工厂

        factory.setHost("127.0.0.1");                                //设置MabbitMQ所在主机ip 或者主机名

        Connection connection=factory.newConnection();               //创建一个连接

        Channel channel=connection.createChannel();                 //创建一个通道

        channel.queueDeclare(Queue_Name,false,false,false,null);    //指定一个队列

        String message="haoxiaoyong zhen shuai";                    //发送消息

        channel.basicPublish("",Queue_Name,null,message.getBytes());//往队列中发送一条消息

        System.out.println("send  "+message);

        channel.close();                                             //关闭频道

        connection.close();                                          //关闭连接

    }

}

package com.hxy.RabbitMQ.haoxiaoyong;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/12/6.
 * com.hxy.RabbitMQ.hxy
 */
public class Received {

    /**
     * Demo1
     * 创建接收方
     */
    private final static String Queue_Name = "queue";                           //队列的名字

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();                     //打开连接

        factory.setHost("127.0.0.1");                                            //设置MabbbitMQ所在的主机和ip

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();                           //创建通道

        channel.queueDeclare(Queue_Name, false, false, false, null);            //声明队列,主要为了防止消息接收者先运行此程序,队列还不存在时创建队列
        //channel.ExchangeDeclare(EXCHANGE_NAME, "fanout");                     //将交换器设置成fanout,就是广播式
        System.out.println("Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer=new QueueingConsumer(channel);                //创建队列消费者

        channel.basicConsume(Queue_Name,true,consumer);                         //指定消费队列

        while (true){

            QueueingConsumer.Delivery delivery=consumer.nextDelivery();         //nextDelivery 是一个堵塞方法（内部实现其实是阻塞队列的take方法）

            String message =new String(delivery.getBody());

            System.out.println("Received  "+message);
        }
    }

}

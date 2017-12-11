package com.hxy.RabbitMQ.hxy;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.hxy.RabbitMQ.model.MessageInfo;
import org.apache.commons.lang.SerializationUtils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Demo2
 * Created by Administrator on 2017/12/7.
 * com.hxy.RabbitMQ.hxy
 */
public class Receiver extends BaseConnector implements Runnable, Consumer {

    public Receiver(String queueName) throws IOException, TimeoutException {

        super(queueName);
    }

    @Override
    public void run() {                                                      //实现Runnble中的run方法

        try {
            channel.basicConsume(queueName, true, this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下面这些方法都是实现Consumer接口的
     *
     * @param consumerTag
     */
    @Override
    public void handleConsumeOk(String consumerTag) {                         //当消费者注册完成自动调用

        System.out.println("Consumer " + consumerTag + " registered");

    }


    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,       //当消费者接受到消息是自动调用
                               AMQP.BasicProperties basicProperties, byte[] body) throws IOException {

        MessageInfo messageInfo = (MessageInfo) SerializationUtils.deserialize(body);

        System.out.println("Message ( "
                + "channel : " + messageInfo.getChannel()
                + " , content : " + messageInfo.getContent()
                + " ) received.");
    }

    /**
     * 下面的这些方法可以暂时不理会
     *
     * @param s
     */

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }


}

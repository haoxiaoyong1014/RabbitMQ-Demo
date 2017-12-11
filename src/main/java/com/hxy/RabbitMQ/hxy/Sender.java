package com.hxy.RabbitMQ.hxy;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * Demo2
 * Created by Administrator on 2017/12/7.
 * com.hxy.RabbitMQ.hxy
 */
public class Sender extends BaseConnector {

    public Sender(String queueName) throws IOException, TimeoutException {

        super(queueName);
    }

    public void sendMessage(Serializable object) throws IOException {

        channel.basicPublish("",queueName,null, SerializationUtils.serialize(object));



    }
}

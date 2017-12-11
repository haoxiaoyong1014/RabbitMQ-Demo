package com.hxy.RabbitMQ.test;

import com.hxy.RabbitMQ.hxy.Receiver;
import com.hxy.RabbitMQ.hxy.Sender;
import com.hxy.RabbitMQ.model.MessageInfo;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Demo2
 * Created by Administrator on 2017/12/7.
 * com.hxy.RabbitMQ.hxy
 */
public class TestRabbieMq {


    public static void main(String[] args) throws IOException, TimeoutException {

        Receiver receiver =new Receiver("testQueue");           //接受者队列的名称

        Thread thread=new Thread(receiver);                     //new 一个线程

        thread.start();                                         //启动此线程

        Sender sender=new Sender("testQueue");                  //发送方队列的名称

       for (int i=0; i<5; i++){

           MessageInfo messageInfo=new MessageInfo();

           messageInfo.setChannel("test");
           messageInfo.setContent("msg"+i);

           sender.sendMessage(messageInfo);

       }


    }

}

package com.hxy.RabbitMQ.msgConfirm;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/8.
 * hxy
 * 测试消息确定机制
 *
 *
 */
/**
 * 定义生产者,对不同的情况进行测试
 *
 */
@RestController
@RequestMapping("/RabbitMQ")
public class Producer {
    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping(value = "/test/{abc}",method = RequestMethod.GET)
    public String test(@PathVariable(value = "abc") String abc){
          rabbitTemplate.setReplyTimeout(10);//设置超时时间
          rabbitTemplate.convertAndSend("testPublishModeExchange","key1", abc + " from RabbitMQ!");
        //amqpTemplate.convertAndSend("testPublishModeQueue1",abc);
        return  abc;
    }
    @RequestMapping(value = "/test1/{abc}",method = RequestMethod.GET)
    public String test1(@PathVariable(value = "abc") String abc){
        rabbitTemplate.convertAndSend("testPublishModeExchange","key11", abc + " from RabbitMQ!");
        return  abc;
    }
    @RequestMapping(value = "/test2/{abc}",method = RequestMethod.GET)
    public String test2(@PathVariable(value = "abc") String abc){
        rabbitTemplate.convertAndSend("testPublishModeExchange1","key1", abc + " from RabbitMQ!");
        return  abc;
    }
    @RequestMapping(value = "/test3/{abc}",method = RequestMethod.GET)
    public String test3(@PathVariable(value = "abc") String abc){
        rabbitTemplate.convertAndSend("testPublishModeExchange1","key11", abc + " from RabbitMQ!");
        return  abc;
    }


    /**
     * 结论:

     1,当发送 curl localhost:9081/api/RabbitMQ/test/123 的时候，也就是说，发送的交换机和路由键都是正确的，结果如下：

     Received111 <123 from RabbitMQ!>
     ================
     correlationData = null
     ack = true
     cause = null
     ================
     2,当发送 curl localhost:9081/api/RabbitMQ/test1/123 的时候，也就是说，发送的交换机是正确的，但是路由键是错误的，结果如下：

     ================
     message = (Body:'123 from RabbitMQ!'MessageProperties [headers={}, timestamp=null, messageId=null, userId=null, appId=null, clusterId=null, type=null, correlationId=null, replyTo=null, contentType=text/plain, contentEncoding=UTF-8, contentLength=0, deliveryMode=PERSISTENT, expiration=null, priority=0, redelivered=null, receivedExchange=null, receivedRoutingKey=null, deliveryTag=0, messageCount=null])
     replyCode = 312
     replyText = NO_ROUTE
     exchange = testPublishModeExchange
     routingKey = key11
     ================
     ================
     correlationData = null
     ack = true
     cause = null
     ================
     3,当发送 curl localhost:9081/api/RabbitMQ/test2/123 的时候，也就是说，发送的交换机是错误的，但是路由键是正确的，结果如下：

     ================
     correlationData = null
     ack = false
     cause = channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'testPublishModeExchange1' in vhost '/', class-id=60, method-id=40)
     ================
     2016-04-21 10:36:07.714 ERROR 43884 --- [ 127.0.0.1:5672] o.s.a.r.c.CachingConnectionFactory       : Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'testPublishModeExchange1' in vhost '/', class-id=60, method-id=40)
     4,当发送 curl localhost:9081/api/RabbitMQ/test2/123 的时候，也就是说，发送的交换机是错误的，但是路由键是错误的，结果如下：

     ================
     correlationData = null
     ack = false
     cause = channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'testPublishModeExchange1' in vhost '/', class-id=60, method-id=40)
     ================
     2016-04-21 10:36:48.269 ERROR 43884 --- [ 127.0.0.1:5672] o.s.a.r.c.CachingConnectionFactory       : Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'testPublishModeExchange1' in vhost '/', class-id=60, method-id=


     结论：观察如上，我们可以发现：

     如果我们发送的消息到达不了交换机，也就是说，发送的交换机写错误了，那么会马上回调 confirm 方法，并且 ack = false，
     同时也会报给你 cause，如 no exchange 'testPublishModeExchange1' in vhost '/'。
     如果我们发送的消息到达交换机，但是路由键写错了，交换机转发不到队列中，confirm 会被回调，并且显示 ack = true，
     意思是 交换机正确收到了消息，但是，同时 returnedMessage 方法也会被调用，会把我们发送的消息返回来。

     */

}

package com.hxy.RabbitMQ;

import com.hxy.RabbitMQ.springBootRabbitMQ.RabbitConfig;
import com.hxy.RabbitMQ.springBootRabbitMQ.Receiver;
import com.hxy.RabbitMQ.springBootRabbitMQ.Sender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Administrator on 2017/12/8.
 * hxy
 */
@Configuration
@Import({RabbitConfig.class})
public class Main {

    @Bean
    public Receiver GetRecBean(){

        return new Receiver();
    }

    @Bean
    public Sender GetSender(){

        return new Sender();
    }
}

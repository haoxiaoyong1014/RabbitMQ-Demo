package com.hxy.RabbitMQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017/12/7.
 * com.hxy.RabbitMQ.hxy
 * Demo3 springBoot整合RabbitMQ
 *
 * 创建应用主类：
 */
@SpringBootApplication(scanBasePackages = {"com.hxy.RabbitMQ"})
public class HelloApplication {

    public static void main(String[] args) {

        SpringApplication.run(HelloApplication.class,args);

    }


}

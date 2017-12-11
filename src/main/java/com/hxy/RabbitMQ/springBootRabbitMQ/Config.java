package com.hxy.RabbitMQ.springBootRabbitMQ;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/8.
 * hxy
 */
@Component
public class Config {

    private String Username;

    private String Password;

    private String Host;

    private int Port;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public int getPort() {
        return Port;
    }

    public void setPort(int port) {
        Port = port;
    }
}

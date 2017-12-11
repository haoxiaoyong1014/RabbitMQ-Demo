package com.hxy.RabbitMQ.model;

import java.io.Serializable;

/**
 * Demo2
 * Created by Administrator on 2017/12/7.
 * com.hxy.RabbitMQ.hxy
 */
public class MessageInfo implements Serializable{

    private static final long serialVersionUID=1L;

    private String channel;                         //渠道

    private String content;                         //来源

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

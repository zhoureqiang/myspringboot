package com.zhirong.service.impl;

import com.zhirong.entity.User;
import com.zhirong.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Copyright (C) 2018 思创数码科技股份有限公司
 * <p>
 * 版权所有。
 * <p>
 *
 * @ClassName ActiveMqServiceImpl
 * @Description TODO
 * @Author zhourq
 * @Date 2019/2/23 21:45
 * @Version 1.0
 **/
@Service
public class ActiveMqServiceImpl implements ActiveMqService {

    //注入由Spring Boot自动产生的jmsTemplate
    @Autowired
    private JmsTemplate jmsTemplate = null;
    @Override
    public void sendMsg(String message) {
        System.out.println("发送消息:{"+message+"}");
        jmsTemplate.convertAndSend(message);
        //自定义发送地址
        //jmsTemplate.convertAndSend("your-destination",message);
    }

    @Override
    //使用注解，监听地址发送过来的消息
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void receiveMsg(String message) {
        System.out.println("接收消息:{"+message+"}");
    }

}

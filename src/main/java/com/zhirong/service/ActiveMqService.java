package com.zhirong.service;

import com.zhirong.entity.User;

/**
 * Copyright (C) 2018 思创数码科技股份有限公司
 * <p>
 * 版权所有。
 * <p>
 *
 * @ClassName ActiveMqService
 * @Description ActiveMQ服务接口
 * @Author zhourq
 * @Date 2019/2/23 21:43
 * @Version 1.0
 **/
public interface ActiveMqService {

    //发送消息
    public void sendMsg(String message);

    //接收消息
    public void receiveMsg(String message);

}

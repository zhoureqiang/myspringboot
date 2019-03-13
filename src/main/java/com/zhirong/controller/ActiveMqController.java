package com.zhirong.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhirong.entity.User;
import com.zhirong.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Copyright (C) 2018 思创数码科技股份有限公司
 * <p>
 * 版权所有。
 * <p>
 *
 * @ClassName ActiveMqController
 * @Description TODO
 * @Author zhourq
 * @Date 2019/2/23 22:39
 * @Version 1.0
 **/
@Controller
@RequestMapping("activemq")
public class ActiveMqController {
    //注入服务对象
    @Autowired
    private ActiveMqService activeMqService = null;

    @ResponseBody
    @RequestMapping("/msg")
    public JSONObject msg(String message){
        JSONObject jsonObject = new JSONObject();
        activeMqService.sendMsg(message);
        jsonObject.put("msg",message);
        return jsonObject;
    }
}

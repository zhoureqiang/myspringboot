package com.zhirong.service;

import com.zhirong.entity.Role;
import com.zhirong.entity.User;

import java.util.Map;

/**
 * Copyright (C) 2018 思创数码科技股份有限公司
 * <p>
 * 版权所有。
 * <p>
 *
 * @ClassName LoginService
 * @Description TODO
 * @Author zhourq
 * @Date 2019/3/15 21:21
 * @Version 1.0
 **/
public interface LoginService {

    //根据用户名查询用户
    public User getUserByName(String name);

    //添加用户
    public User addUser(Map<String,Object> map);

    //添加角色
    public Role addRole(Map<String,Object> map);
}

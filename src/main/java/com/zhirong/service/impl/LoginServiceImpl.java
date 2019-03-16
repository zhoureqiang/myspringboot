package com.zhirong.service.impl;

import com.zhirong.entity.Role;
import com.zhirong.entity.User;
import com.zhirong.service.LoginService;
import com.zhirong.service.RoleService;
import com.zhirong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * Copyright (C) 2018 思创数码科技股份有限公司
 * <p>
 * 版权所有。
 * <p>
 *
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @Author zhourq
 * @Date 2019/3/15 21:28
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {
    //用户接口
    @Autowired
    private UserService userService;
    //角色接口
    @Autowired
    private RoleService roleService;

    //根据用户名查询用户信息
    @Override
    public User getUserByName(String name) {
        return null;
    }

    //新增用户
    @Override
    public User addUser(Map<String, Object> map) {
        return null;
    }

    //新增角色
    @Override
    public Role addRole(Map<String, Object> map) {
        return null;
    }
}

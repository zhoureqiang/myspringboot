package com.zhirong.service.impl;

import com.github.pagehelper.Page;
import com.zhirong.entity.User;
import com.zhirong.mapper.UserMapper;
import com.zhirong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public List<User> getUserByTel(String tel) {
        return null;
    }

    @Override
    public Page<User> findAll() {
        Page<User> list = userMapper.findAll();
        return list;
    }

    @Override
    public List<User> findUser(User user) {
        List<User> list = userMapper.findUser(user);
        return list;
    }

    @Override
    public int deleteUser(String id) {
        userMapper.deleteUser(id);
        return 1;
    }

    @Override
    public User addUser(User user) {
        userMapper.addUser(user);
        return null;

    }

    @Override
    public int editUser(User user) {
        userMapper.editUser(user);
        return 1;
    }
}

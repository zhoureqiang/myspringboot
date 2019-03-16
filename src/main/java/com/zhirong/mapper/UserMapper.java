package com.zhirong.mapper;

import com.github.pagehelper.Page;
import com.zhirong.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserById(String id);

    Page<User> findAll();

    List<User> findUser(User user);

    int deleteUser(String id);

    User addUser(User user);

    int editUser(User user);

}
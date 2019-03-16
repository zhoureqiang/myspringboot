package com.zhirong.service;

import java.util.List;

import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import com.zhirong.entity.User;

import javax.jws.WebService;

/**
 * @Author zhourq
 * @Description 用户信息接口
 * @Date 2019/3/16
 * @Param
 * @return
 **/
public interface UserService {
	public User getUserById(String id);
	public List<User> getUserByTel(String tel);
	public Page<User> findAll();
	public List<User> findUser(User user);

	public int deleteUser(String id);
	public User addUser(User user);
	public int editUser(User user);
}

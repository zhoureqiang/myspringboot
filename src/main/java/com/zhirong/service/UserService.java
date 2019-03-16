package com.zhirong.service;

import java.util.List;

import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import com.zhirong.entity.User;

import javax.jws.WebService;

public interface UserService {
	public User getUserById(int id);
	public List<User> getUserByTel(String tel);
	public Page<User> findAll();
	public List<User> findUser(User user);

	public int deleteUser(int id);
	public User addUser(User user);
	public int editUser(User user);
}

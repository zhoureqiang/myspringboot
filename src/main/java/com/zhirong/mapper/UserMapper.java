package com.zhirong.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zhirong.entity.User;

@Mapper
public interface UserMapper {
	
	public User getUserById(int id);
	public List<User> getUserByTel(String tel);
	public Page<User> findAll();
	public List<User> findUser(User user);

	public void deleteUser(int id);
	public void addUser(User user);
	public void editUser(User user);
}

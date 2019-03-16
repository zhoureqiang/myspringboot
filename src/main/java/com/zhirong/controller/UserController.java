package com.zhirong.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhirong.service.impl.UserServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.zhirong.entity.User;
import com.zhirong.service.UserService;


@Controller
@ComponentScan("com.zhirong.service")
public class UserController {
	@Autowired
	UserService userService;
	
	/**
     * @RestController代表这个类是用Restful风格来访问的，如果是普通的WEB页面访问跳转时，我们通常会使用@Controller
        value = "/users/{username}" 代表访问的URL是"http://host:PORT/users/实际的用户名"
            method = RequestMethod.GET 代表这个HTTP请求必须是以GET方式访问
        consumes="application/json" 代表数据传输格式是json
        @PathVariable将某个动态参数放到URL请求路径中
        @RequestParam指定了请求参数名称
     */

	@RequestMapping("/user/userpage")
	public Object userpage(Model model){
		List<User> users = userService.findAll();
		model.addAttribute("users",users);
		return "/user/userpage";

	}

	@RequestMapping("/user/alluser")
	public String alluser(Model model){
		return "/user/user";
	}

	@ResponseBody
	@RequestMapping("/user/list")
	public Object listuser(@RequestParam(defaultValue = "1") int pageNo,@RequestParam(defaultValue = "10") int pageSize){
		//PageHelper分页
		PageHelper.startPage(pageNo,pageSize);
		PageInfo<User> list = new PageInfo<User>(userService.findAll());
//		System.out.println(list);
		return list;
	}

	@RequestMapping("/user/toAddUser")
	public Object toAddUser(){
		return "/user/user_add";
	}

	@ResponseBody
	@RequestMapping("/user/addUser")
	public void addUser(User user){
		userService.addUser(user);
	}

	@ResponseBody
	@RequestMapping("/user/deleteUserById")
	public Object deleteUserById(String id){
		userService.deleteUser(id);
		return "success";
	}

	@RequestMapping("/user/toEditUser/{id}")
	public Object toEditUser(@PathVariable("id") String id,Model model){
		User user = userService.getUserById(id);
		model.addAttribute("user",user);
		return "user/user_edit";
	}

	@ResponseBody
	@RequestMapping("/user/searchUser")
	public Object searchUser(String user_data){
		User user = new User();
		user.setName(user_data);
		List<User> list = userService.findUser(user);
//		System.out.println(list);
		return list;
	}

	@ResponseBody
	@RequestMapping("/user/updateUser")
	public Object updateUser(User user){
		userService.editUser(user);
		return "success";
	}



}

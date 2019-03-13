package com.zhirong.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhirong.entity.User;
import com.zhirong.service.UserService;
import com.zhirong.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Copyright (C) 2018 思创数码科技股份有限公司
 * <p>
 * 版权所有。
 * <p>
 *
 * @ClassName BaseController
 * @Description TODO
 * @Author zhourq
 * @Date 2019/1/17 21:08
 * @Version 1.0
 **/
@Controller
@ComponentScan("com.zhirong.service")
public class BaseController {


    @Autowired
    private UserService userService;
    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/exportExcel")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        PageHelper.startPage(1,10000);
        PageInfo<User> list = new PageInfo<User>(userService.findAll());

        //excel标题
        String[] title = {"名称","密码","身份证号","手机号码","状态"};

        //excel文件名
        String fileName = "用户信息表"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "用户信息表";

        String[][] content;
        content = new String[list.getList().size()][];
    for (int i = 0; i < list.getList().size(); i++) {

        content[i] = new String[title.length];
        User user = list.getList().get(i);
        content[i][0] = user.getName();
        content[i][1] = user.getPassword();
        content[i][2] = user.getId_card();
        content[i][3] = user.getTel();
        content[i][4] = String.valueOf(user.getStatus());
    }

    //创建HSSFWorkbook
    HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

    //响应到客户端
    try {
        this.setResponseHeader(response, fileName);
               OutputStream os = response.getOutputStream();
               wb.write(os);
               os.flush();
               os.close();
        } catch (Exception e) {
               e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

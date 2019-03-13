package com.zhirong.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * Copyright (C) 2018 思创数码科技股份有限公司
 * <p>
 * 版权所有。
 * <p>
 *
 * @ClassName UploadFileController
 * @Description TODO
 * @Author zhourq
 * @Date 2019/2/14 17:02
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/upload")
public class UploadFileController {


    @ResponseBody
    @RequestMapping(value = "uploadImage")
    public JSONObject uploadImage(File file){
        JSONObject jsonObject = new JSONObject();
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = "/WEB-INF/upload";
        File file1 = new File(savePath);
        if(!file.exists()&&!file.isDirectory()){
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }
        return jsonObject;
    }
}

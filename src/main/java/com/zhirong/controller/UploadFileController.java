package com.zhirong.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhirong.util.FtpFileUtil;
import com.zhirong.util.FtpOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
@Slf4j
public class UploadFileController {

    @Autowired
    FtpOperation ftpOperation;


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


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "上传文件失败");
        String fileName = file.getOriginalFilename();
//        log.info("上传文件:{}", fileName);
        InputStream inputStream = file.getInputStream();
        String filePath = null;
        Boolean flag = ftpOperation.uploadToFtp(inputStream,fileName,false);
        if (flag == true) {
//            log.info("上传文件成功!");

            filePath = ftpOperation.getCURRENT_DIR()+ "/" + fileName;
            System.out.println(filePath);
            map.put("code", "1");
            map.put("msg", "上传文件成功");
        }
        map.put("path", filePath);
        return map; //该路径图片名称，前端框架可用ngnix指定的路径+filePath,即可访问到ngnix图片服务器中的图片
    }

    //ftp处理文件上传
    @RequestMapping(value="/ftpuploadimg", method = RequestMethod.POST)
    public @ResponseBody String ftpUploadImg(@RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) throws IOException {

        String fileName = file.getOriginalFilename();
        InputStream inputStream=file.getInputStream();
        String filePath=null;

        Boolean flag= FtpFileUtil.uploadFile(fileName,inputStream);
        if(flag==true){
            System.out.println("ftp上传成功！");
            filePath=fileName;
        }

        return  filePath;  //该路径图片名称，前端框架可用ngnix指定的路径+filePath,即可访问到ngnix图片服务器中的图片
    }

}

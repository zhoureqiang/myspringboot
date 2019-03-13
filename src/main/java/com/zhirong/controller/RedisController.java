package com.zhirong.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhirong.service.RedisService;
import com.zhirong.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@Component
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    RedisService redisService;

    @Autowired
    @Qualifier("userRedisTemplate")
    private RedisTemplate<String,User>  userRedisTemplate;


    /**
     * 设置Str缓存
     * @param key
     * @param val
     * @return
     */
    @RequestMapping(value = "setStr")
    public String setStr(String key, String val){
        try {
            redisService.setStr(key, val);
            return "success"+key;
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 根据key查询Str缓存
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getStr")
    public JSONObject getStr(String key){
        JSONObject jsonObject = new JSONObject();
        try {
            String val =  redisService.getStr(key);
            if (val==null){
               jsonObject.put("msgText","该键值对不存在");
            }else {
                jsonObject.put("key",key);
                jsonObject.put("val",val);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;

    }


    /**
     * 根据key产出Str缓存
     * @param key
     * @return
     */
    @RequestMapping(value = "delStr")
    public String delStr(String key){
        try {
            redisService.del(key);
            return "success";
        } catch (Exception e){
            return "error";
        }
    }


    /**
     * 设置obj缓存
     * @param key
     * @param user
     * @return
     */
    @RequestMapping(value = "setObj")
    public String setObj(String key, User user){
        try {
            redisService.setObj(key, user);
            return "success";
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 获取obj缓存
     * @param key
     * @return
     */
    @RequestMapping(value = "getObj")
    public Object getObj(String key){
        return redisService.getObj(key);
    }


    /**
     * 删除obj缓存
     * @param key
     * @return
     */
    @RequestMapping(value = "delObj")
    public Object delObj(String key){
        try {
            redisService.delObj(key);
            return "success";
        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    //存储缓存
    @RequestMapping("/template")
    public String redisExample(){
        User user = new User();
        user.setId(199999);
        user.setName("peter1");
        user.setTel("15570065854");
        userRedisTemplate.opsForValue().set("user1",user);
        return "success";
    }

    //获取缓存
    @RequestMapping("/getTemplate")
    public User redisGetExample(){
        return userRedisTemplate.opsForValue().get("user1");
    }


}

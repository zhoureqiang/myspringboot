package com.zhirong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String,String> valueOperationsStr;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object,Object> valueOperationsObj;

    /**
     * 根据指定Key获取String
     */
    public String getStr(String key){
        return valueOperationsStr.get(key);
    }

    /**
     * 设置Str缓存
     */
    public void setStr(String key,String val){
        valueOperationsStr.set(key,val);
    }

    /**
     * 删除指定key
     */
    public void del(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     * 根据指定o获取object
     */
    public Object getObj(Object o){
        return valueOperationsObj.get(o);
    }

    /**
     * 设置Obj缓存
     */
    public void setObj(Object o1,Object o2){
        valueOperationsObj.set(o1,o2);
    }

    /**
     * 根据指定o获取object
     */
    public Object delObj(Object o){
        return redisTemplate.delete(o);
    }

}

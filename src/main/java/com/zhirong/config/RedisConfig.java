package com.zhirong.config;

import com.zhirong.entity.User;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Copyright (C) 2018 思创数码科技股份有限公司
 * <p>
 * 版权所有。
 * <p>
 *
 * @ClassName RedisConfig
 * @Description Redis配置类
 * @Author zhourq
 * @Date 2019/3/4 14:59
 * @Version 1.0
 **/
@Configuration
@EnableCaching
public class RedisConfig {
    /**
     * @Author zhourq
     * 这样就可以使用我们新生成的RedisTemplate了。
     * 其实在源码RedisAutoConfiguration中已经有了此生成RedisTemplate的方法，
     * 只不过是对方法进行了改造一下，生成了一个自定义的RedisTemplate
     **/
    @Bean("userRedisTemplate")
    public RedisTemplate<String,User> redisTemplate(
            RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,User> redisTemplate = new RedisTemplate<String, User>();
        //RedisTemplate会自动初始化StringRedisSerializer，所以这里直接获取
        //RedisSerializer stringRedisSerializer = redisTemplate.getStringSerializer();
        Jackson2JsonRedisSerializer<User> j = new Jackson2JsonRedisSerializer<User>(User.class);
        //设置字符串序列化器，这样Spring就会把Redis的Key当做字符串处理了
        //value的值序列化采用Jackson2JsonRedisSerializer
        redisTemplate.setValueSerializer(j);
        redisTemplate.setHashValueSerializer(j);
        //key的值序列化采用Jackson2JsonRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}

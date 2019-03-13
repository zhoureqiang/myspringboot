package com.zhirong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;


@ServletComponentScan
@SpringBootApplication
@MapperScan("com.zhirong.mapper")
//@ComponentScan("com.zhirong")
@EnableScheduling
public class SpringbootApplication {
	//注入RedisTemplate
//	@Autowired
//	private RedisTemplate redisTemplate = null;

	//定义自定义后初始化方法
//	@PostConstruct
//	public void init(){
//		initRedisTemplate();
//	}

	//设置RedisTemplate的序列化器
//	private void initRedisTemplate(){
//		RedisSerializer serializer = redisTemplate.getStringSerializer();
//		redisTemplate.setKeySerializer(serializer);
//		redisTemplate.setHashKeySerializer(serializer);
//	}


	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}

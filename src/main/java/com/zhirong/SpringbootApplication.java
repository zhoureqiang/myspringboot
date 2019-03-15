package com.zhirong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;


@ServletComponentScan
@SpringBootApplication
//@MapperScan("com.zhirong.mapper")
//@ComponentScan("com.zhirong")
@EnableScheduling
public class SpringbootApplication {
//也可以放在这里
//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		//  单个数据大小
//		factory.setMaxFileSize("102400KB"); // KB,MB
//		/// 总上传数据大小
//		factory.setMaxRequestSize("1024000KB");
//		return factory.createMultipartConfig();
//	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}

package com.example.springbootpro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan({"com.example.springbootpro.mapper","com.example.springbootpro.mh.mapper"})//批量扫描所有mapper接口
public class SpringbootproApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootproApplication.class, args);
	}
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//允许上传的文件最大值
		factory.setMaxFileSize(DataSize.parse("50MB")); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize(DataSize.parse("50MB"));
		return factory.createMultipartConfig();
	}

}



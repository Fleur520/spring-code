package com.zm.anno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
@MapperScan("com.zm.anno.**")
@ComponentScan(basePackages = "com.zm.anno")
public class App {
	public static void main(String[] args) {


		SpringApplication.run(App.class, args);
	}

}

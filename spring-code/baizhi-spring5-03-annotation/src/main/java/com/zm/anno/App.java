package com.zm.anno;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zm.anno.**")
@SpringBootApplication
public class App {
	public static void main(String[] args) {


		SpringApplication.run(App.class, args);
	}

}

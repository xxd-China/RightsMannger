package com.xxd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xxd")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("load complete");
	}

}

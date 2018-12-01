package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootConfiguration
@MapperScan("com.jt.easymall.mapper")
public class StarterEM {
	public static void main(String[] args) {
		SpringApplication.run(StarterEM.class, args);
	}
}

package com.example.cloudclient1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.example.cloudclient1.dao")
public class CloudClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(CloudClient1Application.class, args);
	}

}

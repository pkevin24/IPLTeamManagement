package com.iplmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.iplmanagement.util.CorsConfig;

@SpringBootApplication
@Import(CorsConfig.class)
public class IplmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IplmanagementApplication.class, args);
	}

}

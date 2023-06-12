package com.iplmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.iplmanagement.util.CorsConfig;
import com.iplmanagement.util.JwtRequestFilter;
import com.iplmanagement.util.JwtTokenUtil;

@SpringBootApplication
@Import(CorsConfig.class)
public class IplmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IplmanagementApplication.class, args);
	}
	
	@Bean
	public JwtTokenUtil getUtil() {
		return new JwtTokenUtil();
	}
	
	@Bean
	public JwtRequestFilter getRequest() {
		return new JwtRequestFilter();
	}
}

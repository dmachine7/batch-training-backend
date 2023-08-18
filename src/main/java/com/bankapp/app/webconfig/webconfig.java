package com.bankapp.app.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//
public class webconfig implements WebMvcConfigurer{
	public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**")
		.allowedMethods("*")
		.allowedOrigins("http://localhost:3000")
		.allowedHeaders("*")
		.allowCredentials(false)
		.maxAge(-1);
	}
}

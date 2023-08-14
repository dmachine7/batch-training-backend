package com.bankapp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class bankapplication {

	public static void main(String[] args) {
		SpringApplication.run(bankapplication.class, args);
		System.out.print("hello");
	}

}

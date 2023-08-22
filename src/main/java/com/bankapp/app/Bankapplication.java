package com.bankapp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bankapp.app")
public class Bankapplication {

	public static void main(String[] args) {
		SpringApplication.run(Bankapplication.class, args);
		System.out.print("hello");
	}

}

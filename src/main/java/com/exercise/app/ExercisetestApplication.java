package com.exercise.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.exercise"} )
public class ExercisetestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExercisetestApplication.class, args);
	}

}

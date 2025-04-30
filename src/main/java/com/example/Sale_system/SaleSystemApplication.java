package com.example.Sale_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.Sale_system")
public class SaleSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleSystemApplication.class, args);
	}

}

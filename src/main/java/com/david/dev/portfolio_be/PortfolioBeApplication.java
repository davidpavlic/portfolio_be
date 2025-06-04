package com.david.dev.portfolio_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//TODO: Learn Security
//TODO: Add a cutom header
//TODO: Exception Handler Springboot
/**
 * Main class for the Portfolio Backend Application.
 * This is the entry point of the Spring Boot application.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PortfolioBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioBeApplication.class, args);
	}
}

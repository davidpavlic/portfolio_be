package com.david.dev.portfolio_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PortfolioBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioBeApplication.class, args);
	}

}

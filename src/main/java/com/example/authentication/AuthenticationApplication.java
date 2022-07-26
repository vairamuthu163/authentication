package com.example.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AuthenticationApplication  {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}



}

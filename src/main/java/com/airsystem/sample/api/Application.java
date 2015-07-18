package com.airsystem.sample.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@SpringBootApplication
@EnableJpaRepositories
public class Application {

	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
		return new ApplicationSecurity();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
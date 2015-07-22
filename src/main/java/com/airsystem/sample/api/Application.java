package com.airsystem.sample.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.airsystem.sample.api.service.ApplicationService;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@SpringBootApplication
@EnableJpaRepositories
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Application extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private ApplicationService mApplicationService;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(mApplicationService);
		authProvider.setPasswordEncoder(shaPasswordEncoder);
		auth.authenticationProvider(authProvider);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
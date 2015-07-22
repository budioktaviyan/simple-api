package com.airsystem.sample.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.airsystem.sample.api.utils.Configs;
import com.airsystem.sample.api.utils.Constants;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Configuration
@EnableWebMvcSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers(Configs.URL_LOGIN).permitAll();
		http.authorizeRequests().antMatchers(Configs.URL_CHANGE_PASSWORD).hasAnyAuthority(Configs.ROLE_ADMIN, Configs.ROLE_USER);
		http.authorizeRequests().antMatchers(Configs.URL_USERS).hasAuthority(Configs.ROLE_ADMIN);
		http.authorizeRequests().antMatchers(Configs.URL_EMPLOYEE).hasAnyAuthority(Configs.ROLE_ADMIN, Configs.ROLE_USER);
		http.logout().invalidateHttpSession(Constants.HTTP_INVALIDATE_SESSION).logoutSuccessUrl(Configs.URL_LOGOUT);
	}
}
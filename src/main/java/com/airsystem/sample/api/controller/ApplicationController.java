package com.airsystem.sample.api.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.Users;
import com.airsystem.sample.api.domain.custom.UsersPassword;
import com.airsystem.sample.api.service.UsersService;
import com.airsystem.sample.api.utils.Configs;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RestController
public class ApplicationController {
	private static final Logger LOG = Logger.getLogger(ApplicationController.class.getSimpleName());

	@Resource(name = "authenticationManager")
	private AuthenticationManager mAuthenticationManager;

	@Autowired
	private UsersService mUsersService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, String> applicationLogin(@RequestBody Users users) {
		Map<String, String> jsonObject = new HashMap<String, String>();
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();
		String password = shaPasswordEncoder.encodePassword(users.getPassword(), null);

		LOG.info(String.format("ApplicationController.applicationLogin(username=%s, password=%s)", users.getUsername(), password));
		Authentication appAuthentication = new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword());
		try {
			Authentication authentication = mAuthenticationManager.authenticate(appAuthentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.FORBIDDEN.name());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void applicationLogout(HttpSession httpSession) {
		LOG.info("ApplicationController.applicationLogout()");
		httpSession.invalidate();
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.PUT)
	public Map<String, String> setFixedApplicationPassword(Principal principal, @RequestBody UsersPassword usersPassword) {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();
			String username = principal.getName();
			String oldpassword = shaPasswordEncoder.encodePassword(usersPassword.getOldpassword(), null);
			String newpassword = shaPasswordEncoder.encodePassword(usersPassword.getNewpassword(), null);

			Map credentials = new HashMap();
			credentials.put(Configs.USERNAME, username);
			credentials.put(Configs.USERS_OLD_PASSWORD, oldpassword);
			credentials.put(Configs.USERS_NEW_PASSWORD, newpassword);

			LOG.info(String.format("ApplicationController.setFixedApplicationPassword(username=%s, oldpassword=%s, newpassword=%s)",
									username, oldpassword, newpassword));
			mUsersService.setApplicationPassword(credentials);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}
}
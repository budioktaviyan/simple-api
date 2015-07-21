package com.airsystem.sample.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.custom.UsersPassword;
import com.airsystem.sample.api.service.UsersService;
import com.airsystem.sample.api.utils.Configs;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RestController
public class ApplicationController {
	private static final Logger LOG = Logger.getLogger(ApplicationController.class.getSimpleName());

	@Autowired
	private UsersService mUsersService;

	@RequestMapping(value = "/changepassword", method = RequestMethod.PUT)
	public Map<String, String> setFixedApplicationPassword(@RequestParam String username, @RequestBody UsersPassword usersPassword) {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();
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
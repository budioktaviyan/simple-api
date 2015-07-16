package com.airsystem.sample.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.Roles;
import com.airsystem.sample.api.domain.Users;
import com.airsystem.sample.api.domain.UsersDetail;
import com.airsystem.sample.api.service.UsersService;
import com.airsystem.sample.api.utils.Configs;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RestController
@RequestMapping("/users")
public class UsersController {
	private static final Logger LOG = Logger.getLogger(UsersController.class.getSimpleName());

	@Autowired
	private UsersService mUsersService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Users> findAll() {
		LOG.info("UsersController.findAll()");
		return mUsersService.findAll();
	}

	@RequestMapping(value = "/all/pages", method = RequestMethod.GET)
	public Page<Users> findAllAndPaging(@RequestParam int offset, @RequestParam int size) {
		LOG.info("UsersController.findAllAndPaging()");
		return mUsersService.findAllAndPaging(offset, size);
	}

	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public List<Users> findByNotRoleName(@RequestParam String name) {
		LOG.info(String.format("UsersController.findByNotRoleName(%s)", name));
		return mUsersService.findByNotRoleName(name);
	}

	@RequestMapping(value = "/allusers/pages", method = RequestMethod.GET)
	public Page<Users> findByNotRoleNameAndPaging(@RequestParam String name, @RequestParam int offset, @RequestParam int size) {
		LOG.info(String.format("UsersController.findByNotRoleNameAndPaging(%s)", name));
		return mUsersService.findByNotRoleNameAndPaging(name, offset, size);
	}

	@RequestMapping(value = "/createmodifyusers", method = RequestMethod.POST)
	public Map<String, String> createOrModifyUsers(@RequestBody UsersDetail usersDetail) {
		LOG.info(String.format("UsersController.createOrModifyUsers(%s, %s)",
								usersDetail.getUsers().get(0).getUsername(),
								usersDetail.getRoles().get(0).getName()));
		Map<String, String> jsonObject = new HashMap<String, String>();
		try {
			Users users = usersDetail.getUsers().get(0);
			Roles roles = usersDetail.getRoles().get(0);

			users.setPassword(users.getPassword());
			roles.setUsers(users);
			mUsersService.createOrModifyUsers(users, roles);

			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.toString());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/modifypassword", method = RequestMethod.POST)
	public Map<String, String> modifyUsersPassword(@RequestParam String username,
												   @RequestParam String oldpassword,
												   @RequestParam String newpassword) {
		LOG.info(String.format("UsersController.modifyUsersPassword(%s, %s, %s)", username, oldpassword, newpassword));
		Map<String, String> jsonObject = new HashMap<String, String>();
		try {
			mUsersService.modifyUsersPassword(username, oldpassword, newpassword);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.toString());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}

		return jsonObject;
	}
}
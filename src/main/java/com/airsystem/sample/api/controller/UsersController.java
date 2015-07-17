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
import com.airsystem.sample.api.utils.Constants;

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
		LOG.info(String.format("UsersController.findAllAndPaging(offset=%d, size=%d)",
								offset, size));
		return mUsersService.findAllAndPaging(offset, size);
	}

	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public List<Users> findByNotRoleName(@RequestParam String name) {
		LOG.info(String.format("UsersController.findByNotRoleName(%s)", name));
		return mUsersService.findByNotRoleName(name);
	}

	@RequestMapping(value = "/allusers/pages", method = RequestMethod.GET)
	public Page<Users> findByNotRoleNameAndPaging(@RequestParam String name, @RequestParam int offset, @RequestParam int size) {
		LOG.info(String.format("UsersController.findByNotRoleNameAndPaging(name=%s, offset=%d, size=%d)",
								name, offset, size));
		return mUsersService.findByNotRoleNameAndPaging(name, offset, size);
	}

	@RequestMapping(value = "/createmodifyusers", method = RequestMethod.POST)
	public Map<String, String> createOrModifyUsers(@RequestBody UsersDetail usersDetail) {
		LOG.info(String.format("UsersController.createOrModifyUsers(username=%s, roles=%s)",
								usersDetail.getUsers().get(Constants.FIRST_INDEX).getUsername(),
								usersDetail.getRoles().get(Constants.FIRST_INDEX).getName()));
		Map<String, String> jsonObject = new HashMap<String, String>();
		try {
			Users users = usersDetail.getUsers().get(Constants.FIRST_INDEX);
			Roles roles = usersDetail.getRoles().get(Constants.FIRST_INDEX);
			users.setPassword(users.getPassword());
			roles.setUsers(users);

			mUsersService.createOrModifyUsers(users, roles);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/modifypassword", method = RequestMethod.POST)
	public Map<String, String> modifyUsersPassword(@RequestParam String username,
												   @RequestParam String oldpassword,
												   @RequestParam String newpassword) {
		LOG.info(String.format("UsersController.modifyUsersPassword(username=%s, oldpassword=%s, newpassword=%s)",
								username, oldpassword, newpassword));
		Map<String, String> jsonObject = new HashMap<String, String>();
		try {
			mUsersService.modifyUsersPassword(username, oldpassword, newpassword);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}
}
package com.airsystem.sample.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.Roles;
import com.airsystem.sample.api.domain.Users;
import com.airsystem.sample.api.domain.custom.UsersDetail;
import com.airsystem.sample.api.service.UsersService;
import com.airsystem.sample.api.utils.Configs;
import com.airsystem.sample.api.utils.Constants;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RestController
@RequestMapping(value = "/users")
public class UsersController {
	private static final Logger LOG = LoggerFactory.getLogger(UsersController.class.getSimpleName());

	@Autowired
	private UsersService mUsersService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Users> findAll() {
		LOG.info("UsersController.findAll()");
		return mUsersService.findAll();
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public List<Users> findByRolesName(@RequestParam String rolesname) {
		LOG.info(String.format("UsersController.findByRolesName(%s)", rolesname));
		return mUsersService.findByRolesName(rolesname);
	}

	@RequestMapping(value = "/all/pages", method = RequestMethod.GET)
	public Page<Users> findAllAndPaging(@RequestParam int offset, @RequestParam int size) {
		LOG.info(String.format("UsersController.findAllAndPaging(offset=%d, size=%d)", offset, size));
		return mUsersService.findAllAndPaging(offset, size);
	}

	@RequestMapping(value = "/roles/pages", method = RequestMethod.GET)
	public Page<Users> findByRolesNameAndPaging(@RequestParam String rolesname, @RequestParam int offset, @RequestParam int size) {
		LOG.info(String.format("UsersController.findByRolesNameAndPaging(rolesname=%s, offset=%d, size=%d)", rolesname, offset, size));
		return mUsersService.findByRolesNameAndPaging(rolesname, offset, size);
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public Users findById(@PathVariable Long id) {
		LOG.info(String.format("UsersController.findById(id=%d)", id));
		return mUsersService.findById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, String> save(@RequestBody UsersDetail usersDetail) {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();

			Users users = usersDetail.getUsers().get(Constants.FIRST_INDEX);
			users.setPassword(shaPasswordEncoder.encodePassword(users.getPassword(), null));

			Roles roles = usersDetail.getRoles().get(Constants.FIRST_INDEX);
			roles.setUsers(users);

			LOG.info(String.format("UsersController.save(username=%s, roles=%s)", users.getUsername(), roles.getName()));
			mUsersService.save(users, roles);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Map<String, String> update(@PathVariable Long id, @RequestBody UsersDetail usersDetail) {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();

			Users users = usersDetail.getUsers().get(Constants.FIRST_INDEX);
			users.setId(id);
			users.setUsername(users.getUsername());
			users.setPassword(shaPasswordEncoder.encodePassword(users.getPassword(), null));

			Roles roles = usersDetail.getRoles().get(Constants.FIRST_INDEX);
			roles.setId(id);
			roles.setName(roles.getName());
			roles.setUsers(users);

			LOG.info(String.format("UsersController.save(username=%s, roles=%s)", users.getUsername(), roles.getName()));
			mUsersService.save(users, roles);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Map<String, String> delete(@PathVariable Long id) {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			LOG.info(String.format("UsersController.delete(ID=%d)", id));
			mUsersService.delete(id);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/delete/all", method = RequestMethod.DELETE)
	public Map<String, String> deleteAll() {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			LOG.info("UsersController.deleteAll()");
			mUsersService.deleteAll();
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}
}
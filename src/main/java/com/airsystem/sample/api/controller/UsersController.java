package com.airsystem.sample.api.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.Users;
import com.airsystem.sample.api.service.UsersService;

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
}
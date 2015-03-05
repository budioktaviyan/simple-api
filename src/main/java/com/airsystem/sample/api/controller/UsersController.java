package com.airsystem.sample.api.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.Users;
import com.airsystem.sample.api.repository.IUsersRepository;
import com.airsystem.sample.api.utils.Constants;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RestController
@RequestMapping("/users")
public class UsersController {
	private static final Logger LOG = Logger.getLogger(UsersController.class.getSimpleName());

	@Autowired
	private IUsersRepository mUsersRepository;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Page<Users> findAll() {
		LOG.info("UsersController.findAll()");
		PageRequest pageRequest = new PageRequest(Constants.START_PAGE, Constants.PAGE_SIZE);
		return mUsersRepository.findAll(pageRequest);
	}
}
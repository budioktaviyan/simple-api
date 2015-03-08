package com.airsystem.sample.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airsystem.sample.api.domain.Users;
import com.airsystem.sample.api.repository.IUsersRepository;
import com.airsystem.sample.api.utils.Constants;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Service
@Transactional
public class UsersService {
	private static final Logger LOG = Logger.getLogger(UsersService.class.getSimpleName());

	@Autowired
	private IUsersRepository mUsersRepository;

	public List<Users> findAll() {
		LOG.info("UsersService.findAll()");
		return mUsersRepository.findAll();
	}

	public Page<Users> findAllAndPaging(Integer page) {
		LOG.info("UsersService.findAllAndPaging()");
		PageRequest pageRequest = new PageRequest(page - 1, Constants.PAGE_SIZE);
		return mUsersRepository.findAll(pageRequest);
	}
}
package com.airsystem.sample.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airsystem.sample.api.domain.Roles;
import com.airsystem.sample.api.domain.Users;
import com.airsystem.sample.api.repository.IRolesRepository;
import com.airsystem.sample.api.repository.IUsersRepository;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Service
@Transactional
public class UsersService {
	private static final Logger LOG = Logger.getLogger(UsersService.class.getSimpleName());

	@Autowired
	private IUsersRepository mUsersRepository;

	@Autowired
	private IRolesRepository mRolesRepository;

	public List<Users> findAll() {
		LOG.info("UsersService.findAll()");
		return mUsersRepository.findAll();
	}

	public Page<Users> findAllAndPaging(int offset, int size) {
		LOG.info("UsersService.findAllAndPaging()");
		Pageable pageable = new PageRequest(offset, size);
		return mUsersRepository.findAll(pageable);
	}

	public List<Users> findByNotRoleName(String name) {
		LOG.info(String.format("UsersService.findByNotRoleName(%s)", name));
		return mUsersRepository.findByNotName(name);
	}

	public Page<Users> findByNotRoleNameAndPaging(String name, int offset, int size) {
		LOG.info(String.format("UsersService.findByNotRoleNameAndPaging(%s)", name));
		Pageable pageable = new PageRequest(offset, size);
		return mUsersRepository.findByNotNameAndPaging(name, pageable);
	}

	public void createOrModifyUsers(Users users, Roles roles) {
		LOG.info(String.format("UsersService.createOrModifyUsers(%s, %s)",
								users.getUsername(), roles.getName()));
		mUsersRepository.save(users);
		mRolesRepository.save(roles);
	}
}
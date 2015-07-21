package com.airsystem.sample.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airsystem.sample.api.domain.Roles;
import com.airsystem.sample.api.domain.Users;
import com.airsystem.sample.api.repository.IRolesRepository;
import com.airsystem.sample.api.repository.IUsersRepository;
import com.airsystem.sample.api.utils.Configs;

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
		return mUsersRepository.findAll(sortByUsername());
	}

	public Users findById(Long id) {
		LOG.info(String.format("UsersService.findById(%d)", id));
		return mUsersRepository.findOne(id);
	}

	public Roles findByRolesId(Long id) {
		LOG.info(String.format("UsersService.findByRolesId(%d)", id));
		return mRolesRepository.findOne(id);
	}

	public List<Users> findByRolesName(String rolesname) {
		LOG.info(String.format("UsersService.findByRolesName(%s)", rolesname));
		return mUsersRepository.findByRolesName(rolesname, sortByUsername());
	}

	public Page<Users> findAllAndPaging(int offset, int size) {
		LOG.info(String.format("UsersService.findAllAndPaging(offset=%d, size=%d)", offset, size));
		Pageable pageable = new PageRequest(offset, size, sortByUsername());
		return mUsersRepository.findAll(pageable);
	}

	public Page<Users> findByRolesNameAndPaging(String rolesname, int offset, int size) {
		LOG.info(String.format("UsersService.findByRolesNameAndPaging(rolesname=%s, offset=%d, size=%d)", rolesname, offset, size));
		Pageable pageable = new PageRequest(offset, size, sortByUsername());
		return mUsersRepository.findByRolesName(rolesname, pageable);
	}

	public void save(Users users, Roles roles) {
		LOG.info(String.format("UsersService.save(username=%s, roles=%s)", users.getUsername(), roles.getName()));
		mUsersRepository.save(users);
		mRolesRepository.save(roles);
	}

	public void delete(Long id) {
		LOG.info(String.format("UsersService.delete(ID=%d)", id));
		mUsersRepository.delete(id);
	}

	public void deleteAll() {
		LOG.info("UsersService.deleteAll()");
		mUsersRepository.deleteAll();
	}

	private Sort sortByUsername() {
		return new Sort(Direction.DESC, Configs.USERNAME);
	}
}
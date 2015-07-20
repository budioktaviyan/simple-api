package com.airsystem.sample.api.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airsystem.sample.api.repository.IUsersRepository;
import com.airsystem.sample.api.utils.Configs;
import com.airsystem.sample.api.utils.Constants;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Service
@Transactional
public class ApplicationService {
	private static final Logger LOG = Logger.getLogger(ApplicationService.class.getSimpleName());

	@Autowired
	private IUsersRepository mUsersRepository;

	public Integer setFixedApplicationPassword(Map<String, Object> credentials) throws Exception {
		String username = credentials.get(Configs.USERNAME).toString();
		String oldpassword = credentials.get(Configs.USERS_OLD_PASSWORD).toString();
		String newpassword = credentials.get(Configs.USERS_NEW_PASSWORD).toString();

		LOG.info(String.format("ApplicationService.setFixedApplicationPassword(username=%s, oldpassword=%s, newpassword=%s)",
								username, oldpassword, newpassword));
		Integer result = mUsersRepository.setFixedApplicationPassword(username, oldpassword, newpassword);

		if (result == Constants.EMPTY) {
			throw new Exception("OldPassword is not valid!");
		}
		return result;
	}
}
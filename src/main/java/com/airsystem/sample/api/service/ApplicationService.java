package com.airsystem.sample.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.airsystem.sample.api.domain.Users;
import com.airsystem.sample.api.repository.IUsersRepository;
import com.airsystem.sample.api.utils.Constants;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Service
public class ApplicationService implements UserDetailsService {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationService.class.getSimpleName());

	@Autowired
	private IUsersRepository mUsersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.info(String.format("ApplicationService.loadUserByUsername(%s)", username));
		List<Users> users = mUsersRepository.findByUsername(username);
		if (users.size() == Constants.EMPTY) {
			throw new UsernameNotFoundException("User Not Found!");
		}

		Users credentials = (Users) users.get(Constants.FIRST_INDEX);
		boolean enabled = Constants.IS_ENABLED;
		boolean accountNonExpired = Constants.ACC_NOT_EXPIRED;
		boolean credentialsNonExpired = Constants.CREDENTIAL_NOT_EXPIRED;
		boolean accountNonLocked = Constants.ACC_NOT_LOCKED;

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(credentials.getRoles().getName()));

		User user = new User(credentials.getUsername(), credentials.getPassword(),
							 enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
							 authorities);
		return user;
	}
}
package com.airsystem.sample.api.domain;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@NoRepositoryBean
public class UsersDetail {
	private List<Users> users;
	private List<Roles> roles;

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> pUsers) {
		users = pUsers;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> pRoles) {
		roles = pRoles;
	}
}
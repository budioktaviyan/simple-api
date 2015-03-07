package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.airsystem.sample.api.domain.Users;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */
public interface IUsersRepository extends Repository<Users, Long> {
	List<Users> findAll();
}
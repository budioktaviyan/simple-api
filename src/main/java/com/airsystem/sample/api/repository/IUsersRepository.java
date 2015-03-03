package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.airsystem.sample.api.domain.Users;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Repository
public interface IUsersRepository extends PagingAndSortingRepository<Users, Long> {
	List<Users> findByUsername(String name);
}
package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.airsystem.sample.api.domain.Users;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface IUsersRepository extends PagingAndSortingRepository<Users, Long> {

	List<Users> findByUsername(@Param("username") String username);
}
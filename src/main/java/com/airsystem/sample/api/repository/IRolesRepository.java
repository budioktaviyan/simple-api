package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.airsystem.sample.api.domain.Roles;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface IRolesRepository extends PagingAndSortingRepository<Roles, Long> {

	List<Roles> findByName(@Param("name") String name);
}
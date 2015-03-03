package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.airsystem.sample.api.domain.Roles;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Repository
public interface IRolesRepository extends PagingAndSortingRepository<Roles, Long> {
	List<Roles> findByRolesName(String name);
}
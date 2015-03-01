package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.airsystem.sample.api.domain.Employee;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	List<Employee> findByName(@Param("name") String name);
}
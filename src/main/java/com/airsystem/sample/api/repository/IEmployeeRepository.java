package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.airsystem.sample.api.domain.Employee;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Repository
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	List<Employee> findByEmployeeName(String name);
}
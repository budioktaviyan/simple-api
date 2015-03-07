package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.airsystem.sample.api.domain.Employee;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */
public interface IEmployeeRepository extends Repository<Employee, Long> {
	List<Employee> findAll();
}
package com.airsystem.sample.api.service;

import java.util.List;

import com.airsystem.sample.api.domain.Employee;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */
public interface IEmployeeService {

	List<Employee> findAll();
}
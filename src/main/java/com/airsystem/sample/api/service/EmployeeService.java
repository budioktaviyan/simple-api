package com.airsystem.sample.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airsystem.sample.api.domain.Employee;
import com.airsystem.sample.api.repository.IEmployeeRepository;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Component
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository mEmployeeRepository;

	@Override
	public List<Employee> findAll() {
		return (List<Employee>) mEmployeeRepository.findAll();
	}
}
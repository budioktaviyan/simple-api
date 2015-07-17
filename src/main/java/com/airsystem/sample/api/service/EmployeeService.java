package com.airsystem.sample.api.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airsystem.sample.api.domain.Employee;
import com.airsystem.sample.api.repository.IEmployeeRepository;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Service
@Transactional
public class EmployeeService {
	private static final Logger LOG = Logger.getLogger(EmployeeService.class.getSimpleName());

	@Autowired
	private IEmployeeRepository mEmployeeRepository;

	public List<Employee> findAll() {
		LOG.info("EmployeeService.findAll()");
		return mEmployeeRepository.findAll();
	}

	public Page<Employee> findAllAndPaging(int offset, int size) {
		LOG.info(String.format("EmployeeService.findAllAndPaging(offset=%d, size=%d)",
								offset, size));
		Pageable pageable = new PageRequest(offset, size);
		return mEmployeeRepository.findAll(pageable);
	}

	public Employee createOrModifyEmployee(Employee employee) {
		LOG.info(String.format("EmployeeService.createOrModifyEmployee(%s)", employee.getName()));
		return mEmployeeRepository.saveAndFlush(employee);
	}
}
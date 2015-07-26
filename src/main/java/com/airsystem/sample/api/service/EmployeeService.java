package com.airsystem.sample.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airsystem.sample.api.domain.Employee;
import com.airsystem.sample.api.repository.IEmployeeRepository;
import com.airsystem.sample.api.utils.Configs;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Service
@Transactional
public class EmployeeService {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class.getSimpleName());

	@Autowired
	private IEmployeeRepository mEmployeeRepository;

	public List<Employee> findAll() {
		LOG.info("EmployeeService.findAll()");
		return mEmployeeRepository.findAll(sortByName());
	}

	public Employee findById(Long id) {
		LOG.info(String.format("EmployeeService.findById(%d)", id));
		return mEmployeeRepository.findOne(id);
	}

	public Page<Employee> findAllAndPaging(int offset, int size) {
		LOG.info(String.format("EmployeeService.findAllAndPaging(offset=%d, size=%d)", offset, size));
		Pageable pageable = new PageRequest(offset, size, sortByName());
		return mEmployeeRepository.findAll(pageable);
	}

	public Employee save(Employee employee) {
		LOG.info(String.format("EmployeeService.save(name=%s)", employee.getName()));
		return mEmployeeRepository.saveAndFlush(employee);
	}

	public void delete(Long id) {
		LOG.info(String.format("EmployeeService.delete(ID=%d)", id));
		mEmployeeRepository.delete(id);
	}

	public void deleteAll() {
		LOG.info("EmployeeService.deleteAll()");
		mEmployeeRepository.deleteAll();
	}

	private Sort sortByName() {
		return new Sort(Direction.DESC, Configs.EMPLOYEE_NAME);
	}
}
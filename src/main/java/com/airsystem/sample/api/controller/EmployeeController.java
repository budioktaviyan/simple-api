package com.airsystem.sample.api.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.Employee;
import com.airsystem.sample.api.repository.IEmployeeRepository;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private static final Logger LOG = Logger.getLogger(EmployeeController.class.getSimpleName());

	@Autowired
	private IEmployeeRepository mEmployeeRepository;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Employee> findAll() {
		LOG.info("EmployeeController.findAll()");
		return mEmployeeRepository.findAll();
	}
}
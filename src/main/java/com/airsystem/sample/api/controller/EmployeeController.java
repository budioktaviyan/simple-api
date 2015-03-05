package com.airsystem.sample.api.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.Employee;
import com.airsystem.sample.api.repository.IEmployeeRepository;
import com.airsystem.sample.api.utils.Constants;

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
	public Page<Employee> findAll() {
		LOG.info("EmployeeController.findAll()");
		PageRequest pageRequest = new PageRequest(Constants.START_PAGE, Constants.PAGE_SIZE);
		return mEmployeeRepository.findAll(pageRequest);
	}
}
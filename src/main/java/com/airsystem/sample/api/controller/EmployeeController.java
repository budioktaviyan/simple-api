package com.airsystem.sample.api.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.Employee;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RestController
public class EmployeeController {
	private static final Logger LOG = Logger.getLogger(EmployeeController.class.getSimpleName());

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(name = "/master/employee", method = RequestMethod.GET)
	public Employee getEmployeeData() {
		LOG.info("EmployeeController.getEmployeeData()");

		Long id = counter.incrementAndGet();
		String name = "Budi Oktaviyan Suryanto";
		String gender = "Male";
		String phone = "+628567646893";
		String email = "budi.oktaviyan@icloud.com";
		Date birthdate = Calendar.getInstance().getTime();

		return new Employee(id, name, gender, phone, email, birthdate);
	}
}
package com.airsystem.sample.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airsystem.sample.api.domain.Employee;
import com.airsystem.sample.api.service.EmployeeService;
import com.airsystem.sample.api.utils.Configs;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private static final Logger LOG = Logger.getLogger(EmployeeController.class.getSimpleName());

	@Autowired
	private EmployeeService mEmployeeService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Employee> findAll() {
		LOG.info("EmployeeController.findAll()");
		return mEmployeeService.findAll();
	}

	@RequestMapping(value = "/all/pages", method = RequestMethod.GET)
	public Page<Employee> findAllAndPaging(@RequestParam int offset, @RequestParam int size) {
		LOG.info(String.format("EmployeeController.findAllAndPaging(offset=%d, size=%d)",
								offset, size));
		return mEmployeeService.findAllAndPaging(offset, size);
	}

	@RequestMapping(value = "/createmodifyemployee", method = RequestMethod.POST)
	public Map<String, String> createOrModifyEmployee(@RequestBody Employee employee) {
		LOG.info(String.format("EmployeeController.createOrModifyEmployee(%s)", employee.getName()));
		Map<String, String> jsonObject = new HashMap<String, String>();
		try {
			mEmployeeService.createOrModifyEmployee(employee);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/deleteemployee", method = RequestMethod.DELETE)
	public Map<String, String> deleteEmployee(@RequestParam Long id) {
		LOG.info(String.format("EmployeeController.deleteEmployee(id=%d)", id));
		Map<String, String> jsonObject = new HashMap<String, String>();
		try {
			mEmployeeService.deleteEmployee(id);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}
}
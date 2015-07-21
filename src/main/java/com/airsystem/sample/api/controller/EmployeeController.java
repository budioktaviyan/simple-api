package com.airsystem.sample.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/employee")
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
		LOG.info(String.format("EmployeeController.findAllAndPaging(offset=%d, size=%d)", offset, size));
		return mEmployeeService.findAllAndPaging(offset, size);
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public Employee findById(@PathVariable Long id) {
		LOG.info(String.format("EmployeeController.findById(id=%d)", id));
		return mEmployeeService.findById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, String> save(@RequestBody Employee employee) {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			LOG.info(String.format("EmployeeController.save(name=%s)", employee.getName()));
			mEmployeeService.save(employee);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Map<String, String> update(@PathVariable Long id, @RequestBody Employee employee) {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			LOG.info(String.format("EmployeeController.update(id=%d)", id));
			Employee update = mEmployeeService.findById(id);
			update.setName(employee.getName());
			update.setGender(employee.getGender());
			update.setPhone(employee.getPhone());
			update.setEmail(employee.getEmail());
			update.setBirthdate(employee.getBirthdate());

			mEmployeeService.save(update);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Map<String, String> delete(@PathVariable Long id) {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			LOG.info(String.format("EmployeeController.delete(ID=%d)", id));
			mEmployeeService.delete(id);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}

	@RequestMapping(value = "/delete/all", method = RequestMethod.DELETE)
	public Map<String, String> deleteAll() {
		Map<String, String> jsonObject = new HashMap<String, String>();

		try {
			LOG.info("EmployeeController.deleteAll()");
			mEmployeeService.deleteAll();
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.OK.name());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			jsonObject.put(Configs.JSON_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}

		return jsonObject;
	}
}
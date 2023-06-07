package com.bata.billpunch.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bata.billpunch.constant.ReraMessageConstants;
import com.bata.billpunch.constant.ResponseModel;
import com.bata.billpunch.model.UserDesignationModel;
import com.bata.billpunch.service.impl.UserDesignationServiceimpl;

@RestController
@PropertySource(ignoreResourceNotFound = true, value = "classpath:static/common.properties")
@CrossOrigin(origins = "*")
@RequestMapping("/bill-punch/designation")
public class UserDesignationRestController {
	private static final Logger logger = LogManager.getLogger(UserDesignationRestController.class);
	@Autowired
	private UserDesignationServiceimpl services;

	@Autowired
	private Environment environment;

	/********************************************************************************************
	 * save user designation
	 ********************************************************************************************/
	@PostMapping("/save")
	public ResponseEntity<ResponseModel> saveUserRole(@RequestBody UserDesignationModel entity) {
		UserDesignationModel cm = services.save(entity);
		ResponseModel rs = new ResponseModel();
		if (Optional.ofNullable(cm).isPresent()) {
			logger.debug("Save data Successfully");
			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.SUCCESS_MESSAGE));
			rs.setData(cm);
		} else {
			logger.debug("Failed to save");
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.FAILED_MESSAGE));
		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * Find All UserDept
	 ********************************************************************************************/
	@GetMapping("/getall")
	public ResponseEntity<ResponseModel> getAll() {
		List<UserDesignationModel> cm = services.getAll();
		ResponseModel rs = new ResponseModel();
		if (!cm.isEmpty()) {
			logger.debug("fetch data Successfully");
			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.SUCCESS_MESSAGE));
			rs.setData(cm);
		} else {
			logger.debug("No data found");
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.FAILED_MESSAGE));

		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * Find UserDesignation by Passing Id
	 ********************************************************************************************/
	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel> getByuserId(@PathVariable("id") Long id) {
		UserDesignationModel cm = services.getById(id);
		ResponseModel rs = new ResponseModel();
		if (Optional.ofNullable(cm).isPresent()) {
			logger.debug("fetch data Successfully");
			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.SUCCESS_MESSAGE));
			rs.setData(cm);
		} else {
			logger.debug("No data found");
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.FAILED_MESSAGE));

		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * update user Designation
	 ********************************************************************************************/
	@PutMapping("/modify")
	public ResponseEntity<ResponseModel> updateUserDesignation(@RequestBody UserDesignationModel entity) {
		UserDesignationModel cm = services.update(entity);
		ResponseModel rs = new ResponseModel();
		if (Optional.ofNullable(cm).isPresent()) {
			logger.debug("update data Successfully");
			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.SUCCESS_MESSAGE));
			rs.setData(cm);
		} else {
			logger.debug("Failed to update");
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.FAILED_MESSAGE));

		}
		return ResponseEntity.ok(rs);
	}
}

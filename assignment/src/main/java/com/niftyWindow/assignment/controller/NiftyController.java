package com.niftyWindow.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niftyWindow.assignment.service.NiftyService;
import com.niftyWindow.assignment.utils.NiftyConstants;

@RestController
public class NiftyController {
	
	private static final Logger logger = LoggerFactory.getLogger(NiftyController.class);
	
	@Autowired
	NiftyService niftyService;

	@GetMapping("nifty")
	public ResponseEntity<?> getNiftyData() {
		try {
			logger.info(NiftyConstants.Entered + new Object() {
			}.getClass().getEnclosingMethod().getName());

			Object obj = niftyService.getNiftyData();

			logger.info(NiftyConstants.Exiting + new Object() {
			}.getClass().getEnclosingMethod().getName());
			return new ResponseEntity<Object>(obj, HttpStatus.OK);
		} catch (Exception exception) {
			logger.error(NiftyConstants.EXCEPTION_IN + new Object() {
			}.getClass().getEnclosingMethod().getName(), exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

package com.hclfintech.management.assets.controller.advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hclfintech.management.assets.config.Constants;
import com.hclfintech.management.assets.exception.AssetsManagementException;

@ControllerAdvice
public class ControllerExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);
    @ExceptionHandler(value = AssetsManagementException.class)
    public ResponseEntity<Object> handleApplicationSpecificExceptions(AssetsManagementException ex) {
    	logger.error("ERROR: " + ex.getMessage(), ex);
        return new ResponseEntity<Object>(ex.getMessage(),ex.getHttpMessage());
    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex) {
		logger.error("ERROR: " + ex.getMessage(), ex);
		 return new ResponseEntity<Object>(Constants.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
	}
 
}
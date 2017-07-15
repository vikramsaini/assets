package com.hclfintech.management.assets.exception;

import org.springframework.http.HttpStatus;

public class AssetsManagementException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus httpMessage;

	public AssetsManagementException(Exception e, String message, HttpStatus httpMessage) {
		this.message=message;
		this.httpMessage=httpMessage;
	}
	public AssetsManagementException(String message, HttpStatus httpMessage) {
		this.message=message;
		this.httpMessage=httpMessage;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpMessage() {
		return httpMessage;
	}
	 

}

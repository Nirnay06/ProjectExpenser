package com.expenser.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String errorCode;
	
	public BusinessException() {
	}
	
	public BusinessException(String message) {
		this.message = message;
	}
	
	

}

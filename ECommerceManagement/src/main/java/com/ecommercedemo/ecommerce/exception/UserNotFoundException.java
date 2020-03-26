package com.ecommercedemo.ecommerce.exception;

/**
 * Exception to be throw when the user with provided user Id does not exists.
 * 
 * @author Ruchi
 *
 */
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 602;

	public UserNotFoundException(Long userId) {
		super("User with user Id: " + userId + " not found.");
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}

package com.ecommercedemo.ecommerce.exception;

/**
 * Exception to be throw when the user does not order any thing from provided shop id.
 * 
 * @author Ruchi
 *
 */
public class UsersOrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Integer ERROR_CODE = 604;

	public UsersOrderNotFoundException(Long userId,Long shopId) {
		super("No order found for user id : " + userId + " with shop id: "+ shopId);
	}

	public Integer getErrorCode() {
		return ERROR_CODE;
	}

}

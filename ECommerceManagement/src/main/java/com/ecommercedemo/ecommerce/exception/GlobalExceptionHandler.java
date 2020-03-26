package com.ecommercedemo.ecommerce.exception;
/**
 * Global exception handler for airline management system.
 * 
 * @author Ruchi
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecommercedemo.ecommerce.dto.ExceptionMessageDTO;



@ControllerAdvice
public class GlobalExceptionHandler {	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> productNotFoundExceptionException(ProductNotFoundException productNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(productNotFoundException.getMessage(),
				productNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(ShopNotFoundException.class)
	public ResponseEntity<Object> shopNotFoundExceptionException(ShopNotFoundException shopNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(shopNotFoundException.getMessage(),
				shopNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> userNotFoundExceptionException(UserNotFoundException userNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(userNotFoundException.getMessage(),
				userNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(UsersOrderNotFoundException.class)
	public ResponseEntity<Object> usersOrderNotFoundExceptionException(UsersOrderNotFoundException usersOrderNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(usersOrderNotFoundException.getMessage(),
				usersOrderNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(ProductNameNotFoundException.class)
	public ResponseEntity<Object> productNameNotFoundException(
			ProductNameNotFoundException productNameNotFoundException) {

		ExceptionMessageDTO exceptionMessageDTO = new ExceptionMessageDTO(productNameNotFoundException.getMessage(),
				productNameNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionMessageDTO, HttpStatus.NOT_FOUND);

	}


	
}

package com.ecommercedemo.ecommerce.dto;

public class SuccessResponseDTO {

	String message;
	Integer successCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getSuccessCode() {
		return successCode;
	}

	public void setSuccessCode(Integer successCode) {
		this.successCode = successCode;
	}

	public SuccessResponseDTO(String message, Integer successCode) {
		super();
		this.message = message;
		this.successCode = successCode;
	}

	public SuccessResponseDTO() {

	}
}

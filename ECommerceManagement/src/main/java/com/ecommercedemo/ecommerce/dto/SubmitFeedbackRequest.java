package com.ecommercedemo.ecommerce.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubmitFeedbackRequest {

	@NotNull
	private Long shopId;
	
	@NotNull
	private Long userId;

	@NotNull
	private Double rating;

	@NotNull
	@Size(max = 255)
	private String comment;

	
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

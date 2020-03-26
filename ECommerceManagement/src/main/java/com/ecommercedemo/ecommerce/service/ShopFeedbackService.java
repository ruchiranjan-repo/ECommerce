package com.ecommercedemo.ecommerce.service;

import com.ecommercedemo.ecommerce.dto.SubmitFeedbackRequest;
import com.ecommercedemo.ecommerce.dto.SuccessResponseDTO;

/**
 * Interface for all shopfeedback services.
 * @author Ruchi
 *
 */
public interface ShopFeedbackService {
	
	SuccessResponseDTO submitShopFeedback(SubmitFeedbackRequest submitFeedbackRequest);

}

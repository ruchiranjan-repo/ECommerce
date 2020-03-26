package com.ecommercedemo.ecommerce.restcontroller;

/**
 * Rest controller  for maintaing shop's feedback.
 * @Author Ruchi
 */
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercedemo.ecommerce.dto.SubmitFeedbackRequest;
import com.ecommercedemo.ecommerce.dto.SuccessResponseDTO;
import com.ecommercedemo.ecommerce.service.ShopFeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackRestController {

	@Autowired
	ShopFeedbackService shopFeedbackService;

	/**
	 * Method to submit shop's feedback.
	 * 
	 * @param submitFeedbackRequest submitFeedbackRequest
	 * @return ResponseEntity SuccessResponseDTO
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SuccessResponseDTO> submitFeedback(
			@Valid @RequestBody SubmitFeedbackRequest submitFeedbackRequest) {
		SuccessResponseDTO successResponseDTO = shopFeedbackService.submitShopFeedback(submitFeedbackRequest);

		return new ResponseEntity<>(successResponseDTO, HttpStatus.CREATED);
	}
}
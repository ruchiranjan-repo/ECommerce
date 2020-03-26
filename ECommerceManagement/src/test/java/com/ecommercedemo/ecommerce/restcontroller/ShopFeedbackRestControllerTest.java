package com.ecommercedemo.ecommerce.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.ecommercedemo.ecommerce.TestData;
import com.ecommercedemo.ecommerce.dto.SubmitFeedbackRequest;
import com.ecommercedemo.ecommerce.dto.SuccessResponseDTO;
import com.ecommercedemo.ecommerce.service.ShopFeedbackService;

@SpringBootTest
public class ShopFeedbackRestControllerTest {

	@Autowired
	FeedbackRestController feedbackController;

	@MockBean
	ShopFeedbackService shopFeedbackService;
	
	

	
	SubmitFeedbackRequest submitFeedbackRequest;

	@BeforeEach
	public void setup() {
		submitFeedbackRequest = new SubmitFeedbackRequest();
		submitFeedbackRequest.setComment(TestData.SHOP_FEEDBACK_COMMENT1);
		submitFeedbackRequest.setRating(TestData.SHOP_FEEDBACK_RATING);
		submitFeedbackRequest.setShopId(TestData.SHOP_ID);
		submitFeedbackRequest.setUserId(TestData.USER_ID);
	}

	@Test
	public void testSubmitFeedback() throws Exception {
		SuccessResponseDTO successResponseDTO= new SuccessResponseDTO();
		successResponseDTO.setMessage("Feedback submitted successfully.");
		successResponseDTO.setSuccessCode(621);
		
		when(shopFeedbackService.submitShopFeedback(submitFeedbackRequest)).thenReturn(successResponseDTO);
		
		ResponseEntity<SuccessResponseDTO> responseEntity = feedbackController.submitFeedback(submitFeedbackRequest);

		assertThat(successResponseDTO.getMessage()).isEqualTo(responseEntity.getBody().getMessage());
		assertThat(successResponseDTO.getSuccessCode()).isEqualTo(responseEntity.getBody().getSuccessCode());


	}
	
	

}

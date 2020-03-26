package com.ecommercedemo.ecommerce.service;
/**
 * Implementaion class for all shop feedback services.
 * @author Ruchi
 *
 */
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ecommercedemo.ecommerce.dto.SubmitFeedbackRequest;
import com.ecommercedemo.ecommerce.dto.SuccessResponseDTO;
import com.ecommercedemo.ecommerce.entity.OrderDetails;
import com.ecommercedemo.ecommerce.entity.Shop;
import com.ecommercedemo.ecommerce.entity.ShopFeedback;
import com.ecommercedemo.ecommerce.entity.User;
import com.ecommercedemo.ecommerce.exception.ShopNotFoundException;
import com.ecommercedemo.ecommerce.exception.UserNotFoundException;
import com.ecommercedemo.ecommerce.exception.UsersOrderNotFoundException;
import com.ecommercedemo.ecommerce.repository.OrderDetailsRepository;
import com.ecommercedemo.ecommerce.repository.ShopFeedbackRepository;
import com.ecommercedemo.ecommerce.repository.ShopRepository;
import com.ecommercedemo.ecommerce.repository.UserRepository;

@Service
public class ShopFeedbackServiceImpl implements ShopFeedbackService {
	@Autowired
	ShopRepository shopRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ShopFeedbackRepository shopFeedbackRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	Logger log = Logger.getLogger(ShopFeedbackServiceImpl.class);


	/**
	 * Method to submit shop's feedback.
	 * 
	 * @param submitFeedbackRequest submitFeedbackRequest
	 * @return SuccessResponseDTO successResponseDTO
	 */
	@Override
	public SuccessResponseDTO submitShopFeedback( SubmitFeedbackRequest submitFeedbackRequest) {
		Optional<Shop> shop = shopRepository.findByShopId(submitFeedbackRequest.getShopId());
		if (!shop.isPresent()) {
			log.warn("Shop with shop Id: " + submitFeedbackRequest.getShopId() + " not found.");
			throw new ShopNotFoundException(submitFeedbackRequest.getShopId());
		}
		Optional<User> user = userRepository.findByUserId(submitFeedbackRequest.getUserId());
		if (!user.isPresent()) {
			log.warn("User with user Id: " + submitFeedbackRequest.getUserId() + " not found.");
			throw new UserNotFoundException(submitFeedbackRequest.getUserId());
		}
		List<OrderDetails> orderDetails = orderDetailsRepository.findByShop_shopIdAndUser_userId(submitFeedbackRequest.getShopId(),
				submitFeedbackRequest.getUserId());
		if (CollectionUtils.isEmpty(orderDetails)) {
			log.warn("No order found for user id : " + submitFeedbackRequest.getUserId() + " with shop id: " + submitFeedbackRequest.getShopId());
			throw new UsersOrderNotFoundException(submitFeedbackRequest.getUserId(), submitFeedbackRequest.getShopId());
		}
		
		
		ShopFeedback shopFeedback= new ShopFeedback(submitFeedbackRequest.getRating(), submitFeedbackRequest.getComment(), shop.get(), user.get());
		shopFeedbackRepository.save(shopFeedback);
		List<ShopFeedback> feedbacks= shopFeedbackRepository.findByShop_shopId( submitFeedbackRequest.getShopId());
		if(!CollectionUtils.isEmpty(feedbacks))
		{
			Double averageRating = feedbacks.stream().collect(Collectors.averagingDouble(ShopFeedback::getShopFeedbackRating));
			shop.get().setShopAverageRating(averageRating);
			shopRepository.save(shop.get());
		}
		
		log.info("Feedback submitted successfully.");
		return new SuccessResponseDTO("Feedback submitted successfully.", 621) ;
		
	}

}

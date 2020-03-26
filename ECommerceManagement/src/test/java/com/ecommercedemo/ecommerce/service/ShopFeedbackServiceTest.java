package com.ecommercedemo.ecommerce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.ecommercedemo.ecommerce.TestData;
import com.ecommercedemo.ecommerce.dto.SubmitFeedbackRequest;
import com.ecommercedemo.ecommerce.dto.SuccessResponseDTO;
import com.ecommercedemo.ecommerce.entity.Category;
import com.ecommercedemo.ecommerce.entity.OrderDetails;
import com.ecommercedemo.ecommerce.entity.Product;
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

@SpringBootTest
public class ShopFeedbackServiceTest {

	@Autowired
	ShopFeedbackService shopFeedbackService;

	@MockBean
	ShopFeedbackRepository shopFeedbackRepository;
	@MockBean
	ShopRepository shopRepository;
	@MockBean
	UserRepository userRepository;
	@MockBean
	OrderDetailsRepository orderDetailsRepository;

	Shop shop;
	User user;
	ShopFeedback shopFeedback, shopFeedback1;
	Product product;
	OrderDetails orderDetail;
	Category category;
	List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
	List<ShopFeedback> shopFeedbacks= new ArrayList<ShopFeedback>();
	SubmitFeedbackRequest submitFeedbackRequest;
	
	@BeforeEach
	public void setup() {
		category = new Category();
		category.setCategoryId(TestData.CATEGORY_ID);
		category.setCategoryName(TestData.CATEGORY_NAME);
		category.setCategoryDescription(TestData.CATEGORY_DESCRIPTION);

		product = new Product();
		product.setProductId(TestData.PRODUCT_ID);
		product.setProductCost(TestData.PRODUCT_COST);
		product.setProductDescription(TestData.PRODUCT_DESCRIPTION);
		product.setCategory(category);
		shop = new Shop();
		shop.setShopId(TestData.SHOP_ID);
		shop.setShopName(TestData.SHOP_NAME);
		shop.setShopLocation(TestData.SHOP_LOCATION);
		shop.setShopDescription(TestData.SHOP_DESC);
		shop.setShopAverageRating(TestData.SHOP_AVG_RATING);

		user = new User();
		user.setUserId(TestData.USER_ID);
		user.setUserName(TestData.USER_NAME);

		shopFeedback = new ShopFeedback(TestData.SHOP_FEEDBACK_RATING, TestData.SHOP_FEEDBACK_COMMENT, shop, user);
		shopFeedback.setShopFeedbackId(TestData.SHOP_FEEDBACK_ID);
		shopFeedback1 = new ShopFeedback(TestData.SHOP_FEEDBACK_RATING1, TestData.SHOP_FEEDBACK_COMMENT1, shop, user);
		shopFeedback1.setShopFeedbackId(TestData.SHOP_FEEDBACK_ID2);

		orderDetail = new OrderDetails();
		orderDetail.setOrderId(TestData.ORDERDETAIL_ID);
		orderDetail.setProduct(product);
		orderDetail.setShop(shop);
		orderDetail.setUser(user);
		
		orderDetails.add(orderDetail);
		
		shopFeedbacks.add(shopFeedback);
		shopFeedbacks.add(shopFeedback1);
		
		 submitFeedbackRequest = new SubmitFeedbackRequest ();
		submitFeedbackRequest.setComment(TestData.SHOP_FEEDBACK_COMMENT1);
		submitFeedbackRequest.setRating(TestData.SHOP_FEEDBACK_RATING);
		submitFeedbackRequest.setShopId(TestData.SHOP_ID);
		submitFeedbackRequest.setUserId(TestData.USER_ID);
		

	}

	@Test
	public void testSubmitShopFeedback()
	{
		List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		orderDetails.add(orderDetail);
		List<ShopFeedback> shopFeedbacks= new ArrayList<ShopFeedback>();
		shopFeedbacks.add(shopFeedback);
		shopFeedbacks.add(shopFeedback1);
		
		
		when( orderDetailsRepository.findByShop_shopIdAndUser_userId(TestData.SHOP_ID,
				TestData.USER_ID)).thenReturn(orderDetails);
		when(userRepository.findByUserId(TestData.USER_ID)).thenReturn(Optional.of(user));
		when(shopRepository.findByShopId(TestData.SHOP_ID)).thenReturn(Optional.of(shop));
		when(shopFeedbackRepository.save(shopFeedback)).thenReturn(shopFeedback);
		when(shopFeedbackRepository.findByShop_shopId(TestData.SHOP_ID)).thenReturn(shopFeedbacks);
		
		when(shopRepository.save(Mockito.any(Shop.class))).thenReturn(shop);
		
		
		SuccessResponseDTO successResponseDTO= shopFeedbackService.submitShopFeedback(submitFeedbackRequest);
		
		assertThat(successResponseDTO.getMessage()).isEqualTo("Feedback submitted successfully.");
		assertThat(successResponseDTO.getSuccessCode()).isEqualTo(621);
	}
	
	@Test
	public void testSubmitShopFeedbackThrowsShopNotFoundException()
	{
			
		when(shopRepository.findByShopId(TestData.SHOP_ID)).thenReturn(Optional.empty());
		
		
		assertThrows(ShopNotFoundException.class, () -> {
			shopFeedbackService.submitShopFeedback(submitFeedbackRequest);
		});
		
		
	}
	
	@Test
	public void testSubmitShopFeedbackThrowsUserNotFoundException()
	{
			
		when(userRepository.findByUserId(TestData.USER_ID)).thenReturn(Optional.empty());
		when(shopRepository.findByShopId(TestData.SHOP_ID)).thenReturn(Optional.of(shop));
		
		
		assertThrows(UserNotFoundException.class, () -> {
			shopFeedbackService.submitShopFeedback(submitFeedbackRequest);
		});
		
		
	}
	
	@Test
	public void testSubmitShopFeedbackThrowsUsersOrderNotFoundException()
	{
			

		when( orderDetailsRepository.findByShop_shopIdAndUser_userId(TestData.SHOP_ID,
				TestData.USER_ID)).thenReturn(new ArrayList<>());
		when(userRepository.findByUserId(TestData.USER_ID)).thenReturn(Optional.of(user));
		when(shopRepository.findByShopId(TestData.SHOP_ID)).thenReturn(Optional.of(shop));
		
		
		assertThrows(UsersOrderNotFoundException.class, () -> {
			shopFeedbackService.submitShopFeedback(submitFeedbackRequest);
		});
		
		
	}

	

}

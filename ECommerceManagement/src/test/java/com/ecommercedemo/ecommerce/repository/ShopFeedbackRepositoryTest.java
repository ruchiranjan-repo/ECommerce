package com.ecommercedemo.ecommerce.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.util.CollectionUtils;

import com.ecommercedemo.ecommerce.TestData;
import com.ecommercedemo.ecommerce.entity.Shop;
import com.ecommercedemo.ecommerce.entity.ShopFeedback;
import com.ecommercedemo.ecommerce.entity.User;

@DataJpaTest
public class ShopFeedbackRepositoryTest {
	
	@Autowired
	TestEntityManager testEntityManger;

	@Autowired
	ShopFeedbackRepository shopFeedbackRepository;

	
	Shop shop;
	User user;
	ShopFeedback shopFeedback, shopFeedback1;

	@BeforeEach
	public void setup() {
		shop = new Shop();

		shop.setShopName(TestData.SHOP_NAME);
		shop.setShopLocation(TestData.SHOP_LOCATION);
		shop.setShopDescription(TestData.SHOP_DESC);
		shop.setShopAverageRating(TestData.SHOP_AVG_RATING);

		user = new User();

		user.setUserName(TestData.USER_NAME);

		shopFeedback = new ShopFeedback(TestData.SHOP_FEEDBACK_RATING, TestData.SHOP_FEEDBACK_COMMENT, shop, user);
		shopFeedback1 = new ShopFeedback(TestData.SHOP_FEEDBACK_RATING1, TestData.SHOP_FEEDBACK_COMMENT1, shop, user);

	}

	@Test
	public void testFindByShop_shopId() {
		testEntityManger.persist(shop);
		testEntityManger.persist(user);
		testEntityManger.persist(shopFeedback);
		testEntityManger.persist(shopFeedback1);

		List<ShopFeedback> feedbacks = shopFeedbackRepository.findByShop_shopId(1L);
		
		assertFalse(CollectionUtils.isEmpty(feedbacks));
		assertThat(feedbacks.size()).isEqualTo(2);

	}
	
	@Test
	public void testFindByShop_shopIdNotFound() {
		testEntityManger.persist(shop);
		testEntityManger.persist(user);
		testEntityManger.persist(shopFeedback);
		testEntityManger.persist(shopFeedback1);

		List<ShopFeedback> feedbacks = shopFeedbackRepository.findByShop_shopId(2L);
		
		assertTrue(CollectionUtils.isEmpty(feedbacks));
		

	}

}

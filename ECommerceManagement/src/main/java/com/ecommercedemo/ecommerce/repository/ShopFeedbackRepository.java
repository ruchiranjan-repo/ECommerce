package com.ecommercedemo.ecommerce.repository;
/**
 * Repository class for maintaining shop feedback details.
 * @Author Ruchi
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercedemo.ecommerce.entity.ShopFeedback;

@Repository
public interface ShopFeedbackRepository extends JpaRepository<ShopFeedback, Long>{
	
	List<ShopFeedback> findByShop_shopId(Long shopId);

	
}

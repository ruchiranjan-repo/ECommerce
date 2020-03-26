package com.ecommercedemo.ecommerce.repository;
/**
 * Repository class for maintaining Order details.
 * @Author Ruchi
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercedemo.ecommerce.entity.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{
	
	List<OrderDetails> findByShop_shopIdAndUser_userId(Long shopId,Long userId);
	
}

package com.ecommercedemo.ecommerce.repository;
/**
 * Repository class for maintaining user details.
 * @Author Ruchi
 */
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercedemo.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUserId(Long userId);

}

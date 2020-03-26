package com.ecommercedemo.ecommerce.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercedemo.ecommerce.dto.BuyProductRequestDTO;
import com.ecommercedemo.ecommerce.dto.SuccessResponseDTO;
import com.ecommercedemo.ecommerce.entity.OrderDetails;
import com.ecommercedemo.ecommerce.entity.Product;
import com.ecommercedemo.ecommerce.entity.ProductShopDetails;
import com.ecommercedemo.ecommerce.entity.Shop;
import com.ecommercedemo.ecommerce.entity.User;
import com.ecommercedemo.ecommerce.exception.ProductNotFoundException;
import com.ecommercedemo.ecommerce.exception.ShopNotFoundException;
import com.ecommercedemo.ecommerce.exception.UserNotFoundException;
import com.ecommercedemo.ecommerce.repository.OrderDetailsRepository;
import com.ecommercedemo.ecommerce.repository.ProductRepository;
import com.ecommercedemo.ecommerce.repository.ProductShopDetailsRepository;
import com.ecommercedemo.ecommerce.repository.ShopRepository;
import com.ecommercedemo.ecommerce.repository.UserRepository;

@Service
@org.springframework.transaction.annotation.Transactional
public class ShopServiceImpl implements ShopService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private ProductShopDetailsRepository productShopDetailsRepository;
	@Autowired
	private ProductRepository ProductRepository;
	@Autowired
	private OrderDetailsRepository OrderDetailsRepository;

	@Override
	public SuccessResponseDTO buyProduct(BuyProductRequestDTO buyProductRequestDTO) {
		Optional<User> user = userRepository.findById(buyProductRequestDTO.getUserId());
		if (!user.isPresent()) {
			throw new UserNotFoundException(buyProductRequestDTO.getUserId());
		}
		Optional<Product> product = ProductRepository.findById(buyProductRequestDTO.getProductId());
		if (!product.isPresent()) {
			throw new ProductNotFoundException(buyProductRequestDTO.getProductId());

		}
		Optional<Shop> shop = shopRepository.findById(buyProductRequestDTO.getShopId());

		if (!shop.isPresent()) {
			throw new ShopNotFoundException(buyProductRequestDTO.getShopId());

		}

		ProductShopDetails productShopDetails = productShopDetailsRepository
				.findByShopShopIdAndProductProductId(shop.get().getShopId(),product.get().getProductId());
		productShopDetails.setQuantity(productShopDetails.getQuantity() - 1);
		productShopDetailsRepository.save(productShopDetails);

		OrderDetails details = new OrderDetails();
		details.setProduct(product.get());
		details.setShop(shop.get());
		details.setUser(user.get());
		OrderDetailsRepository.save(details);

		SuccessResponseDTO responseDTO = new SuccessResponseDTO();
		responseDTO.setMessage("The Product Bought Successfully");
		responseDTO.setSuccessCode(600);
		return responseDTO;
	}

}

package com.ecommercedemo.ecommerce.service;

import com.ecommercedemo.ecommerce.dto.BuyProductRequestDTO;
import com.ecommercedemo.ecommerce.dto.SuccessResponseDTO;

public interface ShopService {
	public SuccessResponseDTO buyProduct(BuyProductRequestDTO buyProductRequestDTO);
	

}

package com.ecommercedemo.ecommerce.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercedemo.ecommerce.dto.BuyProductRequestDTO;
import com.ecommercedemo.ecommerce.dto.SuccessResponseDTO;
import com.ecommercedemo.ecommerce.service.ShopService;

@RestController
@RequestMapping("/shops")
public class ShopRestController {
	@Autowired
	private ShopService shopService;
	
	@PostMapping("")
	public ResponseEntity<SuccessResponseDTO> buyProduct(@RequestBody BuyProductRequestDTO buyProductRequestDTO)
	{
		SuccessResponseDTO successResponseDTO=shopService.buyProduct(buyProductRequestDTO);
		return new ResponseEntity<>(successResponseDTO,HttpStatus.CREATED);
		
	}

}

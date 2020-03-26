package com.ecommercedemo.ecommerce.restcontroller;



import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.ecommercedemo.ecommerce.dto.BuyProductRequestDTO;
import com.ecommercedemo.ecommerce.dto.SuccessResponseDTO;
import com.ecommercedemo.ecommerce.entity.Product;
import com.ecommercedemo.ecommerce.entity.Shop;
import com.ecommercedemo.ecommerce.entity.User;
import com.ecommercedemo.ecommerce.service.ShopService;

@RunWith(MockitoJUnitRunner.class)
public class TestShopController {
	@Mock
	private ShopService shopService;
	@InjectMocks
	private ShopRestController productShopRestController;
	Product product = null;
	Shop shop = null;
	SuccessResponseDTO successResponsedto=null;
	BuyProductRequestDTO buyProductRequestDTO=null; 
	User  user=null;

	@Before
	public void setup() {
		product = new Product();
		product.setProductId(1L);
		shop = new Shop();
		shop.setShopId(1L);
		successResponsedto=new SuccessResponseDTO();
		successResponsedto.setMessage("The Product Bought Successfully");
		successResponsedto.setSuccessCode(600);
		buyProductRequestDTO=new BuyProductRequestDTO();
		buyProductRequestDTO.setProductId(1L);
		buyProductRequestDTO.setUserId(1L);
		buyProductRequestDTO.setShopId(1L);
		user=new User();
		user.setUserId(2L);
		
		

	}
	@Test
	public void testBuyProduct()
	{
		
		
		Mockito.when(shopService.buyProduct(buyProductRequestDTO)).thenReturn(successResponsedto);
		ResponseEntity<SuccessResponseDTO> response=productShopRestController.buyProduct(buyProductRequestDTO);
		assertThat(successResponsedto.getMessage()).isEqualTo(response.getBody().getMessage());
		assertThat(successResponsedto.getSuccessCode()).isEqualTo(response.getBody().getSuccessCode());

	}

}
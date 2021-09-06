package com.shop.elementgames;

import java.math.BigDecimal;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.elementgames.models.Product;
import com.shop.elementgames.models.User;
import com.shop.elementgames.repository.ProductRepository;
import com.shop.elementgames.repository.UserRepository;

@Component
public class DataSeeder {
	
	@Autowired private ProductRepository productRepository;
	@Autowired private UserRepository userRepository;
	
	@PostConstruct
	void seed() {
		Product product1 = new Product();
		product1.setId(UUID.fromString("e9582513-0d04-42ed-9faf-622a04b83d54"));
		product1.setName("God of War");
		product1.setDescription("Kratos");
		product1.setPrice(new BigDecimal("18.99"));
		product1.setImage("https://d1pqlgpcx1bu0k.cloudfront.net/static/img/GOW-OG-image.jpg");
		
		productRepository.save(product1);
		
		Product product2 = new Product();
		product2.setId(UUID.fromString("32bcfa9d-5857-4d5b-b3fb-c1b2a728e3a0"));
		product2.setName("Read Dead Redemption");
		product2.setDescription("John Marston");
		product2.setPrice(new BigDecimal("18.99"));
		product2.setImage("https://pbs.twimg.com/media/EF4k1KNXkAEg-4o.jpg");
		
		productRepository.save(product2);
		
		
		User user = new User();
		
		user.setProfileId(UUID.fromString("3ea53969-8dec-4dd8-9c85-da0531d38e89"));
		user.setEmail("abc@gmail.com");
		user.setName("abc");
		//translates to 12345
		user.setEncodedPassword("$2a$10$KQk./ciekRp/Srf/1RjUvOTT7OBD3zklVfZmM4EqV7V2ymGSMq6AK");
		user.setAdminAccount(false);
		
		userRepository.save(user);
		
		

		
	}
}

package com.shop.elementgames.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.elementgames.authentication.models.UserPrincipal;
import com.shop.elementgames.exceptions.ClientInputException;
import com.shop.elementgames.exceptions.ProductNotFoundException;
import com.shop.elementgames.models.CartProduct;
import com.shop.elementgames.models.CartProductDto;
import com.shop.elementgames.models.MyProfile;
import com.shop.elementgames.models.Product;
import com.shop.elementgames.models.User;
import com.shop.elementgames.models.dto.AddToCartRequest;
import com.shop.elementgames.models.dto.ChangeCountRequest;
import com.shop.elementgames.repository.CartProductRepository;
import com.shop.elementgames.repository.ProductRepository;
import com.shop.elementgames.repository.UserRepository;

@RestController
@RequestMapping(path = "/api/myprofile")
public class MyProfileController {
	
	
	@Autowired private CartProductRepository cartProductRepository;
	@Autowired private UserRepository userRepository;
	@Autowired private ProductRepository productRepository;
	
	private Logger logger = LoggerFactory.getLogger(MyProfileController.class);
	
	@GetMapping("")
	public MyProfile getAllProducts(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		UUID profileId = userPrincipal.getProfileId();
		logger.debug("getting profile for {}" ,profileId);
		
		User user = userRepository.findById(profileId).orElseThrow();
		
		return new MyProfile(user.getEmail(),user.getName());
	}
	
	@GetMapping("/cart")
	public List<CartProductDto> getCart(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		UUID profileId = userPrincipal.getProfileId();
		List<CartProduct> cartProducts =  cartProductRepository.findAllByProfileId(profileId);
		return populateDtoWithProductDetails(cartProducts);
	}
	
	
	
	
	@PostMapping(path = "/cart" )
	//upsert requires serializable
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public CartProductDto addToCart(@AuthenticationPrincipal UserPrincipal userPrincipal, 
			@RequestBody @Valid AddToCartRequest addToCartRequest) {
		UUID profileId = userPrincipal.getProfileId();
		UUID productId = addToCartRequest.getProductId();
		int count = addToCartRequest.getCount();
		
		checkIfProductAlreadyExistsInCart(profileId, productId);
		Product product = checkIfProductExists(productId);
		CartProduct cartProduct = populateCartProductFromDetails(profileId, productId, count);
		cartProductRepository.save(cartProduct);

		return new CartProductDto(product, count);
	}
	
	
	@DeleteMapping("/cart/{productId}")
	public ResponseEntity<Void> removeProductFromCart(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@PathVariable("productId") UUID productId){
		UUID profileId = userPrincipal.getProfileId();
		cartProductRepository.deleteByProfileIdAndProductId(profileId, productId);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PatchMapping("/cart/{productId}")
	public ResponseEntity<Void> editProductCountFromCart(@AuthenticationPrincipal UserPrincipal userPrincipal,
			@RequestBody @Valid ChangeCountRequest changeCountRequest,@PathVariable("productId") UUID productId){
		UUID profileId = userPrincipal.getProfileId();
		
		CartProduct cartProduct =  cartProductRepository.findOneByProfileIdAndProductId(profileId, productId)
				.orElseThrow(() ->new ClientInputException("please enter a productid that is in the cart"));
		
		cartProduct.setCount(changeCountRequest.getCount());

		cartProductRepository.save(cartProduct);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	// utility functions
	private List<CartProductDto> populateDtoWithProductDetails(List<CartProduct> cartProducts) {
		List<UUID> allProductIds = cartProducts.stream()
				.map(CartProduct::getProductId).collect(Collectors.toList());

		Map<UUID, Product> productIdToProduct =  productRepository.findAllById(allProductIds).stream()
			.collect(Collectors.toMap(Product::getId, product -> product));
		
		return cartProducts.stream()
			.map( cp -> new CartProductDto(productIdToProduct.get(cp.getProductId()),cp.getCount()))
			.collect(Collectors.toList());
	}
	
	private CartProduct populateCartProductFromDetails(UUID profileId, UUID productId, int count) {
		CartProduct cartProduct = new CartProduct();
		cartProduct.setDatabaseId(UUID.randomUUID());
		cartProduct.setCount(count);
		cartProduct.setProductId(productId);
		cartProduct.setProfileId(profileId);
		return cartProduct;
	}
	
	private Product checkIfProductExists(UUID productId) {
		Optional<Product> product = productRepository.findById(productId);
		
		if(product.isEmpty()) 
			throw new ProductNotFoundException();
		return product.orElseThrow();
	}
	
	private void checkIfProductAlreadyExistsInCart( UUID profileId , UUID productId ) {
		Optional<CartProduct> cartProduct =  cartProductRepository.findOneByProfileIdAndProductId(profileId, productId);
		cartProduct.ifPresent((cp) -> {
			throw new ClientInputException("this product is already in cart");
		});
	}
	
}

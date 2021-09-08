package com.shop.elementgames.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.elementgames.models.CartProduct;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, UUID> {
	
	public List<CartProduct> findAllByProfileId(UUID profileId);
	
	public Optional<CartProduct> findOneByProfileIdAndProductId(UUID profileId , UUID productId);
	
	public int deleteByProfileIdAndProductId(UUID profileId , UUID productId); 

}

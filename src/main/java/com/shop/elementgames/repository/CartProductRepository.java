package com.shop.elementgames.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.elementgames.models.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, UUID> {

}

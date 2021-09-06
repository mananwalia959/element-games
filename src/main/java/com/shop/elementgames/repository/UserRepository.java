package com.shop.elementgames.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.elementgames.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	Optional<User> findOneByEmail(String email);
}

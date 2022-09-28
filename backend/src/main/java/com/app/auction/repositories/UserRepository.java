package com.app.auction.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.auction.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	Optional<User> findByEmail(String email);

	/*
	 * @Query("select u from User u join fetch u.roles where u.email=?1")
	 * Optional<User> findByEmail(String email);
	 */
}

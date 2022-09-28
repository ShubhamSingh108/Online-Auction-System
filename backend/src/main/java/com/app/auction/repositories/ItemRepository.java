package com.app.auction.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.auction.entities.Item;
import com.app.auction.entities.User;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByUser(User user);

}

package com.app.auction.services;

import java.util.List;

import com.app.auction.dto.ItemDto;



public interface ItemServices {

	List<ItemDto> getByUserId(int itemID);

	ItemDto createItem(ItemDto itemDto,int Userid);

	ItemDto getById(int itemID);

	List<ItemDto> getAllItems();

	List<String> getAllCategories();

	ItemDto updateItem(ItemDto item, int itemID);

	String deleteItem(int itemID);

}

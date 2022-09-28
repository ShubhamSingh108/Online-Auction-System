package com.app.auction.ServiceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.auction.dto.ItemDto;
import com.app.auction.entities.Category;
import com.app.auction.entities.Item;
import com.app.auction.entities.User;
import com.app.auction.exception.ResourceNotFoundException;
import com.app.auction.repositories.ItemRepository;
import com.app.auction.repositories.UserRepository;
import com.app.auction.services.ItemServices;

@Service
@Transactional
public class ItemServiceImpl implements ItemServices {
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ItemDto createItem(ItemDto itemDto,int userId) {
		Item item = modelMapper.map(itemDto, Item.class);
		User user = userRepo.findById(userId).orElseThrow(() -> new
				  ResourceNotFoundException("User", " id", userId));
				 item.setUser(user);
		Item saveItem = this.itemRepo.save(item);
		return modelMapper.map(saveItem, ItemDto.class);

	}
	
	@Override
	public List<ItemDto> getByUserId(int userID) {
		User user = userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));
		
		List<Item> items = itemRepo.findByUser(user);

        List<ItemDto> itemDtos = items.stream().map((item) -> this.modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());

        return itemDtos;
		
	}

	
	  @Override public ItemDto getById(int itemID) { Item item =
	  this.itemRepo.findById(itemID) .orElseThrow(() -> new
	  ResourceNotFoundException("Item", "id", itemID)); return
	  modelMapper.map(item, ItemDto.class);
	  
	  }
	 

	@Override
	public List<ItemDto> getAllItems() {
		List<Item> items = this.itemRepo.findAll();

		List<ItemDto> itemDto = items.stream().map((item) -> this.modelMapper.map(item, ItemDto.class))
				.collect(Collectors.toList());
		return itemDto;

	}

	@Override
	public List<String> getAllCategories() {

		List<String> categories = new ArrayList<>();

		for (Category type : Category.values()) {
			categories.add(type.toString());
		}

		return categories;
	}

	/*
	 * @Override public List<ItemDto> getByCategory(String category) {
	 * 
	 * Item items = this.itemRepo.findAllByCategory(category) .orElseThrow(() -> new
	 * ResourceNotFoundException("Item", "category", category)); List<Item>
	 * itemList= this.itemRepo.findAllByItem(items);
	 * 
	 * List<ItemDto> itemDto = itemList.stream().map((item) ->
	 * this.modelMapper.map(item, ItemDto.class)) .collect(Collectors.toList());
	 * 
	 * return itemDto;
	 * 
	 * 
	 * }
	 */

	/*
	 * @Override public List<ItemDto> createdByUser(int userId) { User user =
	 * this.userRepo.findById(userId) .orElseThrow(() -> new
	 * ResourceNotFoundException("User", "user id", userId));
	 * 
	 * List<Item> items = this.itemRepo.findByUsers(user); List<ItemDto> itemDto =
	 * items.stream().map((item) -> this.modelMapper.map(item, ItemDto.class))
	 * .collect(Collectors.toList());
	 * 
	 * return itemDto; }
	 */

	@Override
	public ItemDto updateItem(ItemDto itemDto, int itemID) {
		Item item = this.itemRepo.findById(itemID)
				.orElseThrow(() -> new ResourceNotFoundException("Item", "id", itemID));
		item.setItemName(itemDto.getItemName());

		item.setImageURL(itemDto.getImageURL());
		item.setDescription(itemDto.getDescription());
		item.setPurchaseDate(itemDto.getPurchaseDate());
		item.setPrice(itemDto.getPrice());

		Item updateItem = this.itemRepo.save(item);
		ItemDto itemDto1 = this.modelMapper.map(updateItem, ItemDto.class);
		return itemDto1;
	}

	@Override
	public String deleteItem(int itemID) {
		String mesg = "Deletion of item details failed!!!!!!!!!!!";

		if (itemRepo.existsById(itemID)) {
			itemRepo.deleteById(itemID);
			mesg = "item details deleted successfully , for item id :" + itemID;
		}

		return mesg;

	}

}

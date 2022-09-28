package com.app.auction.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.auction.dto.ItemDto;
import com.app.auction.services.FileService;
import com.app.auction.services.ItemServices;


@RestController
@RequestMapping("/api/")
@Validated
@CrossOrigin("*")
public class ItemController {
	@Autowired
	private ItemServices itemService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/items")
	public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto ItemDto
			,@PathVariable int userId) {
		ItemDto createItemDto = itemService.createItem(ItemDto,userId);
		return new ResponseEntity<ItemDto>(createItemDto, HttpStatus.CREATED);
		
	}

	@PutMapping("/items/{itemId}")
	public ResponseEntity<ItemDto> updateItem(@Valid @RequestBody ItemDto ItemDto, @PathVariable("itemId") int id) {
		ItemDto updateUser = itemService.updateItem(ItemDto, id);
		return new ResponseEntity<ItemDto>(updateUser, HttpStatus.OK);
	}

	@DeleteMapping("/items/{itemID}")
	public ResponseEntity<?> deleteItem(
			@PathVariable @Range(min = 1, max = 100, message = "Invalid item id!!!") int itemID) {
		System.out.println("in delete");
		this.itemService.deleteItem(itemID);
		return ResponseEntity.ok(Map.of("message", "detailed deleted sucessfully"));
	}

	@GetMapping("/items")

	public ResponseEntity<List<ItemDto>> getAllItems() {
		return ResponseEntity.ok(this.itemService.getAllItems());

	}

	
	  @GetMapping("/items/{itemId}") public ResponseEntity<ItemDto>
	  getById(@PathVariable int itemId) { return
	  ResponseEntity.ok(this.itemService.getById(itemId));
	  
	  }
	 
	@GetMapping("/user/{userId}/items")
	public ResponseEntity<List<ItemDto>> getByUserId(@PathVariable int userId) {
		List<ItemDto> items = itemService.getByUserId(userId);
		return new ResponseEntity<List<ItemDto>>(items, HttpStatus.OK);
		

	}


	// item image upload
	@PostMapping("/items/{itemId}/image")
	public ResponseEntity<ItemDto> uploadItemImage(@RequestParam("image") MultipartFile image, @PathVariable int itemId)
			throws IOException {
		ItemDto itemDto = itemService.getById(itemId);
		String fileName = fileService.uploadImage(path, image);

		itemDto.setImageURL(fileName);
		ItemDto updateItem = itemService.updateItem(itemDto, itemId);
		return new ResponseEntity<ItemDto>(updateItem, HttpStatus.OK);
	}

	@GetMapping(value = "/items/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}

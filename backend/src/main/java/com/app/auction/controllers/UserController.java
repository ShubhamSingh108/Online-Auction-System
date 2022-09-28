package com.app.auction.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.app.auction.config.AppConstants;
import com.app.auction.dto.ApiResponse;
import com.app.auction.dto.UserDto;
import com.app.auction.dto.UserResponse;
import com.app.auction.services.UserService;

@RestController
@RequestMapping("/api/")
@Validated
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);

	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer uid) {
		UserDto updateUser = this.userService.updateUser(userDto, uid);
		return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
	}

//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable @Range(min = 1, max = 100, message = "Invalid emp id!!!") int userId) {
		System.out.println("in delete");
		return userService.deleteUser(userId);
		// return ResponseEntity.ok(Map.of("message", "detailed deleted sucessfully"));
	}

	@GetMapping("/users")
	public ResponseEntity<UserResponse> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize) {
		UserResponse userResponse = this.userService.getAllUsers(pageNumber, pageSize);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);

	}
	/*
	 * @DeleteMapping("/user/{userId}") public ApiResponse deletedUser(
	 * 
	 * @PathVariable @Range(min = 1, max = 100, message = "Invalid emp id!!!") int
	 * userId) { System.out.println("in delete");
	 * this.userService.deleteUser(userId); return new
	 * ApiResponse("detailed deleted sucessfully",true); }
	 */

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));

	}

}

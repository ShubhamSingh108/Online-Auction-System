package com.app.auction.services;

import java.util.List;

import com.app.auction.dto.UserResponse;
import com.app.auction.dto.UserDto;

public interface UserService {
	UserDto registerNewUser(UserDto userDto);
UserDto createUser(UserDto user);
UserDto updateUser(UserDto user,Integer userId);
UserDto getUserById(Integer userId);
UserResponse getAllUsers(int pageNumber,int pageSize);
String deleteUser(int userId);
//void deletedUser(int userId);


}

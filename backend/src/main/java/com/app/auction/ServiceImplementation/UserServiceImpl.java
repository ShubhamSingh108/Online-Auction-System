package com.app.auction.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.auction.config.AppConstants;
import com.app.auction.dto.UserDto;
import com.app.auction.dto.UserResponse;
import com.app.auction.entities.Role;
import com.app.auction.entities.Status;
import com.app.auction.entities.User;
import com.app.auction.exception.ResourceNotFoundException;
import com.app.auction.repositories.RoleRepository;
import com.app.auction.repositories.UserRepository;
import com.app.auction.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private ModelMapper modelMapper;
	// fetch address details by using repo
	@Autowired
	private PasswordEncoder encoder;

	
	  @Override 
	  public UserDto createUser(UserDto userDto)
	  { 
		  User user = dtoToUser(userDto);
		  User saveUser = userRepo.save(user);
		  return userToDto(saveUser); }
	 

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setDob(userDto.getDob());
		user.setStatus(Status.ACTIVE);

		User updateUser = userRepo.save(user);
		UserDto userDto1 = userToDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return userToDto(user);
	}

	@Override
	public UserResponse getAllUsers(int pageNumber, int pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<User> pagePost = userRepo.findAll(p);
		List<User> users = pagePost.getContent();
		List<UserDto> userDto = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		UserResponse userReponse = new UserResponse();
		userReponse.setContent(userDto);
		userReponse.setPageNumber(pagePost.getNumber());
		userReponse.setPageSize(pagePost.getSize());
		userReponse.setTotalElements(pagePost.getTotalElements());
		userReponse.setLastPage(pagePost.isLast());
		return userReponse;

	}

	public User dtoToUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public String deleteUser(int userId) {

		String mesg = "Deletion of userid details failed!!!!!!!!!!!";

		if (userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			mesg = "user details deleted successfully , for user id :" + userId;
		}
		/*
		 * User user = userRepo.findById(userId) .orElseThrow(() -> new
		 * ResourceNotFoundException("User", "user id", userId)); userRepo.delete(user);
		 */

		return mesg;
	}

	

	/*
	 * @Override public void deletedUser(int userId) { User user=
	 * userRepo.findById(userId).orElseThrow(()->new
	 * ResourceNotFoundException("User","id", userId)); userRepo.delete(user);
	 * 
	 * }
	 */

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		// password encode
		user.setPassword(encoder.encode(user.getPassword()));
		Role role = roleRepo.findById(AppConstants.USER).get();
		user.getRoles().add(role);
		User newUser = userRepo.save(user);
		return modelMapper.map(newUser, UserDto.class);
	}
}

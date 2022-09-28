package com.app.auction.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.auction.dto.JwtAuthRequest;
import com.app.auction.dto.JwtAuthResponse;
import com.app.auction.dto.UserDto;
import com.app.auction.entities.User;
import com.app.auction.exception_handler.ApiException;
import com.app.auction.security.JwtTokenHelper;
import com.app.auction.services.UserService;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtTokenHelper jwtHelper;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = jwtHelper.generateToken(userDetails);
		
	
		
		JwtAuthResponse response = new JwtAuthResponse();
		//response.setJwt(token);
		 response.setToken(token);
		// response.se(mapper.map((User) userDetails, UserDto.class));
		 response.setUser(this.mapper.map((User) userDetails, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}

	}

	// register new user api

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) {
		UserDto registeredUser = userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

	/*
	 * // get loggedin user data
	 * 
	 * @GetMapping("/current-user/") public ResponseEntity<UserDto>
	 * getUser(Principal principal) { User user =
	 * this.userRepo.findByEmail(principal.getName()).get(); return new
	 * ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
	 * }
	 */

}

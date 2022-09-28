package com.app.auction.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.auction.entities.User;
import com.app.auction.exception.ResourceNotFoundException;
import com.app.auction.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from db by username
		User user=this.userRepo.findByEmail(username)
		.orElseThrow(()-> new ResourceNotFoundException("User","email:"+username));
		
		return user;
	}

}

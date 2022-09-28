package com.app.auction.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class JwtAuthRequest {
	//@NotBlank(message = "Email can't be blank or null")
	@Email(message = "Invalid Email")
	@Column(unique = true)
    private String username;
	@NotBlank(message = "password can't be blank or null")
	private String password;

}

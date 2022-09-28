package com.app.auction.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.app.auction.entities.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
	private int userId;

	@NotEmpty(message = "First name must be supplied")
	@Length(min = 4, max = 30, message = "Invalid First name length")
	private String firstName;

	@NotEmpty(message = "Last name must be supplied")
	private String lastName;

	// @Size(min = 5, message = "char must be min 4 and max 100 allowed")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,100})")
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = "password must be supplied")
	private String password;
	
	private Set<RoleDto> roles = new HashSet<>();
	@NotEmpty
	@Email(message = "Invalid Email")
	private String email;

	private LocalDate dob;

	@Enumerated(EnumType.STRING)
	private Status status;

	@NotEmpty(message = "address must be supplied")
	@Length(min = 4, max = 100, message = "Invalid length")

	private String address;
	@NotEmpty(message = "city  must be supplied")
	

	private String city;

	@NotEmpty(message = "state  must be supplied")

	private String state;

	@NotEmpty(message = "country must be supplied")
	private String country;
	
	@Min(value=3, message = "pincode must be supplied") 

	private int pincode;
	//private Set<ItemDto> items = new HashSet<>();
	//private Set<EventDto> events = new HashSet<>();
	/*
	 * private AddressDto address;
	 * 
	 * @JsonIgnore public String getPassword() { return this.password; }
	 * 
	 * @JsonProperty public void setPassword(String password) { this.password =
	 * password; }
	 * 
	 * 
	 */}

package com.app.auction.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.app.auction.dto.UserDto;
import com.app.auction.entities.Category;
import com.app.auction.entities.Status;
import com.app.auction.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
	private int itemID;
	@Length(min = 4, max = 30)
	@NotEmpty(message = "Please enter the Item Name")
	private String itemName;

	@NotBlank(message = "Please give a Description of the Item")
	private String description;

	private LocalDate purchaseDate;

	@NotNull
	@Range(min = 500, max = 50000, message = "Minimum Price of Item should be greater than 0")
	private float price;

	@JsonProperty(access = Access.READ_ONLY)
	// @NotBlank(message="must attach image with Item")
	private String imageURL;
	//@Enumerated(EnumType.STRING)
	//private Status status;
	@Enumerated(EnumType.STRING)
	private Category category;
	
	private EventDto event;

	private UserDto user;


	public ItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}

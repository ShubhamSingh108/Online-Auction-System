package com.app.auction.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.app.auction.entities.Event;
import com.app.auction.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class EventEnrolmentDto {
	
	private int enrolmentId;
	
	
	  @NotNull 
	  private EventDto eventDto;
	 
	
	  @NotNull 
	  private UserDto userDto;
	 
	@NotNull
	@Min(1)
	private float bidAmount;
	
}

package com.app.auction.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private String message;
	private boolean success;
	//private LocalDateTime timeStamp;

	
	  public ApiResponse(String message) {
		  super(); this.message = message;
//	  this.timeStamp = LocalDateTime.now();
		  }
	 
		
}

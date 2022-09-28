package com.app.auction.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorResponse {
	private String message;
	private LocalDateTime timeStamp;

	public ErrorResponse(String message) {
		super();
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}

}

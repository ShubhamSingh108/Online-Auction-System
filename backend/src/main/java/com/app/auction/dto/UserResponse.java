package com.app.auction.dto;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
	private List<UserDto> content;
	private int PageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPage;
	private boolean lastPage;
	

}

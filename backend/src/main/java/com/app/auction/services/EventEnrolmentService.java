package com.app.auction.services;

import java.util.List;

import com.app.auction.dto.EventEnrolmentDto;
import com.app.auction.entities.*;


public interface EventEnrolmentService {
	
	public String registerEnrolment(EventEnrolmentDto enrolDto);



	 EventEnrolment getEnrolmentById(int enromentId);

	 EventEnrolmentDto getById(int enromentId);

	public String cancelEnEnrolmentByUser(int enrolId);
	
	public void placeBid(EventEnrolment enrolDto);


}

package com.app.auction.ServiceImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.auction.entities.*;
import com.app.auction.dto.EventEnrolmentDto;
import com.app.auction.dto.ItemDto;
import com.app.auction.dto.UserDto;
import com.app.auction.exception.ResourceNotFoundException;
import com.app.auction.exception_handler.QuickAuctionException;
import com.app.auction.repositories.EnrolmentRepository;
import com.app.auction.repositories.UserRepository;
import com.app.auction.services.EmailService;
import com.app.auction.services.EventEnrolmentService;

@Service
@Transactional
public class EnrolmentServiceImpl implements EventEnrolmentService {

	@Autowired
	private EnrolmentRepository enrolRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmailService emaiService;

	@Override
	public String registerEnrolment(EventEnrolmentDto enrolDto) {
		String message = "Enrolment successful";
		EventEnrolment enrolment = mapper.map(enrolDto, EventEnrolment.class);
		Event event = enrolment.getEvent();
		if (event.getUser().getUserId() == enrolment.getUser().getUserId()) {
			throw new QuickAuctionException("seller can not enroll  for the auction");
		}
		enrolRepo.findByEventAndUser(event, enrolment.getUser()).ifPresent(e -> {
			throw new QuickAuctionException("can not enrolled for the auction twice");
		});

		if (LocalDateTime.now().isAfter(event.getStartDate())) {
			throw new QuickAuctionException("Cannot register for the auction after the start date");
		}
		enrolRepo.save(enrolment);
		return message;
	}

	

	
	@Override
	public EventEnrolment getEnrolmentById(int enrolId) {
		EventEnrolment enrolment = enrolRepo.findById(enrolId)
				.orElseThrow(() -> new ResourceNotFoundException("EventEnrolment", "id", enrolId));
		return enrolment;
	}

	

	@Override
	public String cancelEnEnrolmentByUser(int enrolId) {
		String msg = "enrolment cancel by user";
		EventEnrolment events = enrolRepo.findById(enrolId)
				.orElseThrow(() -> new ResourceNotFoundException("EventEnrolment", "id", enrolId));
		enrolRepo.delete(events);
		return msg;
	}

	@Override
	public void placeBid(EventEnrolment enrolment) {
		System.out.println("Placing bid : " + enrolment.toString());
		Event event = enrolment.getEvent();
		if (LocalDateTime.now().isAfter(event.getEndDate()) || LocalDateTime.now().isBefore(event.getStartDate())) {
			throw new QuickAuctionException("Bid can be placed between the auction start and end date & time");
		}
		Item item = event.getItem();
		if (enrolment.getBidAmount() < item.getPrice()) {
			throw new QuickAuctionException("Cannot place bid less than item price");
		}
		enrolment.setBidTime(LocalDateTime.now());
		EventEnrolment enrolmentWinner = enrolRepo.save(enrolment);
		String message = "Congratulations,yor are winner";
		String Subject = "Congratulation";
		String to = enrolmentWinner.getUser().getEmail();
		// String to="dipti.kushwaha@gmail.com";
		String from = "onlineauction22@gmail.com";
		emaiService.sendEmail(message, Subject, to, from);

	}




	@Override
	public EventEnrolmentDto getById(int enromentId) {
		EventEnrolment enrol = this.enrolRepo.findById( enromentId)
				.orElseThrow(() -> new ResourceNotFoundException("EventEnrolment", "id",  enromentId));
		return mapper.map(enrol, EventEnrolmentDto.class); 
	
	}


}

package com.app.auction.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.auction.entities.*;
import com.app.auction.dto.EventEnrolmentDto;
import com.app.auction.dto.ItemDto;
import com.app.auction.dto.EventDto;
import com.app.auction.dto.UserDto;

import com.app.auction.services.EventEnrolmentService;
import com.app.auction.services.EventService;
import com.app.auction.services.UserService;

@RestController
@RequestMapping("/api/")
@Validated
public class EnrolmentController {

	@Value("${placeBid.success.response}")
	private String BID_SUCCESS_MESSAGE;

	@Autowired
	private EventEnrolmentService enrolmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@PostMapping("/user/{userId}/event/{eventId}/enrolment")
	public ResponseEntity<String> enrolmentInEvent(@PathVariable Integer userId, @PathVariable Integer eventId) {

		UserDto userDto = userService.getUserById(userId);
		EventDto eventDto = eventService.getEventById(eventId);
		EventEnrolmentDto enrolmentDto = EventEnrolmentDto.builder().eventDto(eventDto).userDto(userDto).build();

		String message = enrolmentService.registerEnrolment(enrolmentDto);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);

	}

//	TODO Get all enrolment by userId

	@PutMapping("/enrolment/{id}")
	public ResponseEntity<String> bidInEvent(@PathVariable Integer id, @RequestBody EventEnrolmentDto enrolmentDto) {
		EventEnrolment dbEnrolment = enrolmentService.getEnrolmentById(id);
		System.out.println("Conroller: Fetch enro from DB : " + dbEnrolment.toString());
		dbEnrolment.setBidAmount(enrolmentDto.getBidAmount());
		enrolmentService.placeBid(dbEnrolment);
		System.out.println("Conroller :  After setting amoutn from DB : " + dbEnrolment.toString());
		return new ResponseEntity<String>(BID_SUCCESS_MESSAGE, HttpStatus.OK);
	}

	@GetMapping("/enrolment/{Id}")
	public ResponseEntity<EventEnrolment> getEnrolmentById(@Valid @PathVariable int Id) {
		return ResponseEntity.ok(this.enrolmentService.getEnrolmentById(Id));

	}

	@GetMapping("/enrolments/{enrolId}")
	public ResponseEntity<EventEnrolmentDto> getById(@Valid @PathVariable int enrolId) {
		EventEnrolmentDto  enrolment= enrolmentService.getById(enrolId);
		return new ResponseEntity<EventEnrolmentDto>(enrolment, HttpStatus.OK);

	}
	@DeleteMapping("/enrolments/{enrolId}")
	public ResponseEntity<?> cancelEnEnrolmentByUser(
			@PathVariable @Range(min = 1, max = 100, message = "Invalid item id!!!") int enrolId) {
		System.out.println("in delete");
		enrolmentService.cancelEnEnrolmentByUser(enrolId);
		return ResponseEntity.ok(Map.of("message", "detailed deleted sucessfully"));
	}

}


  package com.app.auction.controllers;
  
  import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import
  org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import
  org.springframework.web.bind.annotation.DeleteMapping;
import
  org.springframework.web.bind.annotation.GetMapping;
import
  org.springframework.web.bind.annotation.PathVariable;
import
  org.springframework.web.bind.annotation.PostMapping;
import
  org.springframework.web.bind.annotation.PutMapping;
import
  org.springframework.web.bind.annotation.RequestBody;
import
  org.springframework.web.bind.annotation.RequestMapping;
import
  org.springframework.web.bind.annotation.RestController;

import com.app.auction.dto.EventDto;
import com.app.auction.dto.ItemDto;
import com.app.auction.services.EventService;
  @Validated
  @RestController
  @RequestMapping("/api/") //@Validated
  public class EventController 
  {
	  @Autowired
	  private EventService eventService;
	  @PostMapping("/user/{userId}/item/{itemId}/events")
  public ResponseEntity<EventDto> createNewEvent(@Valid @RequestBody EventDto eventDto,@PathVariable int userId,
		  @PathVariable int itemId) { 
	  EventDto createEventDto =eventService.createNewEvent(eventDto,userId,itemId);
	  return new ResponseEntity<EventDto>(createEventDto, HttpStatus.CREATED);
	
  }
  
  @DeleteMapping("/events/{eventId}")
  public ResponseEntity<?> deleteEvent(	@PathVariable @Range(min = 1, max = 100, message = "Invalid item id!!!")int eventId)
  {
  eventService.deleteEvent(eventId);
  return ResponseEntity.ok(Map.of("message", "event deleted sucessfully"));
  
  }
  
  @GetMapping("/events") 
  public ResponseEntity<List<EventDto>> getAllEvents()
  {
  return ResponseEntity.ok(eventService.getAllEvents());
  
  }
  
  @GetMapping("/events/{eventId}") 
  public ResponseEntity<EventDto>getEventById(@PathVariable int eventId)
  {
	  return ResponseEntity.ok(eventService.getEventById(eventId));
  
  }
  @GetMapping("/user/{userId}/event")
	public ResponseEntity<List<EventDto>> getByUserId(@PathVariable int userId) {
		List<EventDto> events = eventService.getByUserId(userId);
		return new ResponseEntity<List<EventDto>>(events, HttpStatus.OK);
		

	}
  
  
  @PutMapping("/events/{eventId}")
  public ResponseEntity<EventDto>updateEvent(@Valid @RequestBody EventDto eventDto, @PathVariable @Range(min = 1, max = 100, message = "Invalid item id!!!")int eventId) {
  EventDto updateEvent = eventService.updateEvent(eventDto, eventId);
  return new ResponseEntity<EventDto>(updateEvent, HttpStatus.OK);
 
	}

  }
 

package com.app.auction.services;

import java.util.List;

import com.app.auction.dto.EventDto;
import com.app.auction.entities.Event;

public interface EventService {

	public List<EventDto> getAllEvents();

	
	EventDto getEventById(int eventId);

	public EventDto createNewEvent(EventDto eventdto,int userId,int itemId);

	
	// delete event 
	void deleteEvent(int eventId);

	// update event 
	public EventDto updateEvent(EventDto event, int eventId);

	List<EventDto> getByUserId(int userId);

	public void declareWinner();

}


package com.app.auction.ServiceImplementation;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.app.auction.dto.EventDto;
import com.app.auction.entities.Event;
import com.app.auction.entities.EventEnrolment;
import com.app.auction.entities.Item;
import com.app.auction.entities.Status;
import com.app.auction.entities.User;
import com.app.auction.exception.ResourceNotFoundException;
import com.app.auction.repositories.EnrolmentRepository;
import com.app.auction.repositories.EventRepository;
import com.app.auction.repositories.ItemRepository;
import com.app.auction.repositories.UserRepository;
import com.app.auction.services.EventService;

@Service

@Transactional
public class EventServiceImpl implements EventService {
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	private EventRepository eventRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private EnrolmentRepository enrolmentRepository;

	@Override
	public List<EventDto> getAllEvents() {
		List<Event> events = eventRepo.findAll();
		List<EventDto> eventDto = events.stream().map((event) -> mapper.map(event, EventDto.class))
				.collect(Collectors.toList());
		return eventDto;

	}

	@Override
	public EventDto getEventById(int eventId) {

		Event events = eventRepo.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));

		return mapper.map(events, EventDto.class);
	}

	@Override
	public EventDto createNewEvent(EventDto eventDto, int userId, int itemId) {
		Event event = mapper.map(eventDto, Event.class);
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id", userId));
		Item item = itemRepo.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("item", " id", itemId));
		event.setItem(item);
		event.setUser(user);
		event.setStatus(Status.ACTIVE);
		Event newEvent = eventRepo.save(event);
		return mapper.map(newEvent, EventDto.class);
	}

	/*
	 * @Override public List<EventDto> getByItemId(int itemId) { Item item =
	 * itemRepo.findById(itemId).orElseThrow(() -> new
	 * ResourceNotFoundException("Item", "Id", itemId)); List<Event> events =
	 * eventRepo.findByItem(item); List<EventDto> eventDto =
	 * events.stream().map((event) -> mapper.map(event, EventDto.class))
	 * .collect(Collectors.toList()); return eventDto; }
	 */

	@Override
	public void deleteEvent(int eventId) {
		Event event = this.eventRepo.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));
		eventRepo.delete(event);

	}

	@Override
	public EventDto updateEvent(EventDto eventDto, int eventId) {
		Event event1 = this.eventRepo.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));
		event1.setStartDate(eventDto.getStartDate());
		event1.setEndDate(eventDto.getEndDate());
		Event newEvent = eventRepo.save(event1);
		return mapper.map(newEvent, EventDto.class);

	}

	@Override
	public List<EventDto> getByUserId(int userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new

		ResourceNotFoundException("User", "Id", userId));
		List<Event> events = eventRepo.findByUser(user);
		List<EventDto> eventDto = events.stream().map((event) -> mapper.map(event, EventDto.class))
				.collect(Collectors.toList());
		return eventDto;

	}
	
	  @Override
	  @Scheduled(initialDelay = 15, fixedDelay = 60, timeUnit = TimeUnit.SECONDS)
	  public void declareWinner() { 
		  LOG.info("Start of schedular to declare winner for auctions ");
		  List<Event> events = eventRepo.findCompletedEvents(); 
		  for (Event event : events) {
			  try {
				  LOG.info("Finding winner for Auction event " + event.getEventId());
				  List<EventEnrolment> enrols = enrolmentRepository.findByEvent(event);
				  if (enrols.isEmpty()) {
					  LOG.info("No enrolment for event : " + event.getEventId());  
				  }
				  else
				  {
					  EventEnrolment highestEnrolment = enrols.stream()
							  .max(Comparator.comparing(EventEnrolment::getBidAmount)
									  .thenComparing(EventEnrolment::getBidTime))
							  .orElseThrow(() -> 
							  new Exception("Highest enrolement no found for Event ID " + event.getEventId()));
					  event.setWinner(highestEnrolment.getUser());
					  event.setStatus(Status.WINNER_DECLARED);
					  eventRepo.save(event);
				  }
			  }
			  catch(Exception exception) {				  
				  LOG.info("Exception occured in Schedular job. " + exception.getLocalizedMessage());
			  }
			  LOG.info("End of schedular to declare winner for auctions ");
		  }
	  
		  // Require methods to get enrolments by Event ID
	  
	  }
	 
}
